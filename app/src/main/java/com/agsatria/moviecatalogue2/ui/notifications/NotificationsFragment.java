package com.agsatria.moviecatalogue2.ui.notifications;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.agsatria.moviecatalogue2.DetailMovieActivity;
import com.agsatria.moviecatalogue2.R;
import com.agsatria.moviecatalogue2.adapter.MovieAdapter;
import com.agsatria.moviecatalogue2.adapter.TvAdapter;
import com.agsatria.moviecatalogue2.model.Movie;
import com.agsatria.moviecatalogue2.model.MovieResponse;
import com.agsatria.moviecatalogue2.network.ApiClient;
import com.agsatria.moviecatalogue2.network.ApiInterface;
import com.agsatria.moviecatalogue2.network.Constant;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationsFragment extends Fragment implements MovieAdapter.ListItemClickListener, TvAdapter.ListItemClickListener {

    private RecyclerView rvMovie;
    private List<Movie> list = new ArrayList<>();
    private String[] tvJudul, tvDesc, tvRelease;
    private TypedArray ivPhoto;
    private TvAdapter movieAdapter;
    private ProgressBar pgBar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        rvMovie = root.findViewById(R.id.rv_list);
        rvMovie.setHasFixedSize(true);
        
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvMovie = view.findViewById(R.id.rv_list);
        pgBar = view.findViewById(R.id.pg_bar);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        movieAdapter = new TvAdapter(getActivity());
        pgBar.setVisibility(View.VISIBLE);

        setLayout();
        getMovie();

    }

    private void setLayout(){
        rvMovie.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMovie.setHasFixedSize(true);
        rvMovie.setAdapter(movieAdapter);
    }

    private void getMovie() {
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        Call<MovieResponse> call = service.getTvShow(Constant.API_KEY, "en-US");
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                if (response.body() != null) {
                    MovieResponse apiResponse = response.body();
                    list = apiResponse.getMoviePopulars();
                    pgBar.setVisibility(View.GONE);
                    movieAdapter.setListMovie(list, NotificationsFragment.this);
                    movieAdapter.notifyDataSetChanged();


                }

            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });

    }

    @Override
    public void onListItemClick(int clickedItemIndex, Movie movie) {
        movie = list.get(clickedItemIndex);

        Movie movies = new Movie();
        movies.setOriginalTitle(movie.getOriginalName());
//        movie.setMoviePoster(movie.getMoviePoster());
        movies.setPlotSynopsis(movie.getPlotSynopsis());
        movies.setReleaseDate(movie.getFirstAir());


        Intent pindahData = new Intent(getActivity(), DetailMovieActivity.class);
        pindahData.putExtra("BLA", movie.getMoviePoster());
        pindahData.putExtra(DetailMovieActivity.EXTRA_MOVIE, movies);
        startActivity(pindahData);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
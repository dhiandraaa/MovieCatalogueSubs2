package com.agsatria.moviecatalogue2.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.agsatria.moviecatalogue2.R;
import com.agsatria.moviecatalogue2.model.Movie;
import com.agsatria.moviecatalogue2.network.Constant;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private List<Movie> listMovie = new ArrayList<>();
    private static final String TAG = MovieAdapter.class.getSimpleName();
    private ListItemClickListener mOnClickListener;
    private Context context;

    public MovieAdapter(Context context) {
        this.context = context;
    }

    public interface ListItemClickListener{
        void onListItemClick(int clickedItemIndex, Movie movie);
    }

    public void setListMovie(List<Movie> movieList, ListItemClickListener mOnClickListener){
        if (movieList == null)return;
        this.listMovie.clear();
        this.listMovie.addAll(movieList);
        this.mOnClickListener = mOnClickListener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_row_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, final int position) {
        final Movie movie = listMovie.get(position);

        holder.tvJudul.setText(movie.getOriginalTitle());
        Picasso.with(context).load(Constant.IMAGE_URL + movie.getMoviePoster()).placeholder(R.drawable.ic_launcher_background).fit().centerCrop().into(holder.ivPhoto);
        Log.i(TAG, "onBindViewHolder: Poster " + Constant.IMAGE_URL + movie.moviePoster);
        holder.tvDesc.setText(movie.getPlotSynopsis());
        holder.tvRilis.setText(movie.getReleaseDate());
//        Glide.with(context).load(Constant.IMAGE_URL + movie.moviePoster).placeholder(R.drawable.ic_launcher_foreground).dontAnimate().into(holder.ivPhoto);

        final Movie movie1 = listMovie.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnClickListener.onListItemClick(position, movie1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listMovie.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPhoto;
        TextView tvJudul, tvDesc, tvRilis;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            tvJudul = itemView.findViewById(R.id.tv_judul);
            tvDesc = itemView.findViewById(R.id.tv_deskripsi);
            tvRilis = itemView.findViewById(R.id.tv_release_date);
            ivPhoto = itemView.findViewById(R.id.iv_poster);
        }
    }
}

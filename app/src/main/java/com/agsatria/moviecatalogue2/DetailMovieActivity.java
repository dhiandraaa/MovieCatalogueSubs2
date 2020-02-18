package com.agsatria.moviecatalogue2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.agsatria.moviecatalogue2.model.Movie;
import com.agsatria.moviecatalogue2.network.Constant;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class DetailMovieActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "extra_movie";

    private TextView tvJudul, tvDesc, tvRelease;
    private ImageView ivPhoto;
    private ProgressBar pgBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        tvDesc = findViewById(R.id.tv_ddeskripsi);
        tvJudul = findViewById(R.id.tv_djudul);
        tvRelease = findViewById(R.id.tv_drelease);
        ivPhoto = findViewById(R.id.iv_photo);
        pgBar = findViewById(R.id.pg_bar);
        pgBar.setVisibility(View.VISIBLE);

        Movie movie = this.getIntent().getParcelableExtra(EXTRA_MOVIE);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(movie.getOriginalTitle());
        Intent getData = this.getIntent();

        tvJudul.setText(movie.getOriginalTitle());
        tvRelease.setText(movie.getReleaseDate());
        tvDesc.setText(movie.getPlotSynopsis());
        Picasso.with(DetailMovieActivity.this)
                .load(Constant.IMAGE_URL + getData.getStringExtra("BLA"))
                .placeholder(R.mipmap.ic_launcher)
                .into(ivPhoto);
        if (tvJudul == null) {
            tvJudul.setText(movie.getOriginalName());
            tvRelease.setText(movie.getFirstAir());
        }
        pgBar.setVisibility(View.GONE);
    }
}

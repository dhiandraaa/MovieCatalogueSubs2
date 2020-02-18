package com.agsatria.moviecatalogue2.network;

import com.agsatria.moviecatalogue2.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("discover/movie")
    Call<MovieResponse> getMoviePopular(@Query("api_key") String apiKey,
                                        @Query("language") String language);

    @GET("discover/tv")
    Call<MovieResponse> getTvShow(@Query("api_key") String apiKey,
                                  @Query("language") String language);

}

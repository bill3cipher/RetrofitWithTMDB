package com.example.nsc.retrofit.manager.http;

import com.example.nsc.retrofit.model.MovieObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by NSC on 8/17/2017.
 */

public interface ApiService {
    @GET("movie/top_rated")
    Call<MovieObject> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<MovieObject.ResultsBean> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);
}

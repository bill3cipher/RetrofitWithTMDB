package com.example.nsc.retrofit.manager;

import com.example.nsc.retrofit.model.Service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by NSC on 8/30/2017.
 */

public class ApiClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(Service.MOVIE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


    public static Retrofit getClientGoogle(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(Service.MOVIE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


}

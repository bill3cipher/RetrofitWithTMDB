package com.example.nsc.retrofit.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.nsc.retrofit.R;
import com.example.nsc.retrofit.adapter.MovieAdapter;
import com.example.nsc.retrofit.manager.ApiClient;
import com.example.nsc.retrofit.manager.http.ApiService;
import com.example.nsc.retrofit.model.MovieObject;
import com.example.nsc.retrofit.model.Service;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ArrayList<MovieObject.ResultsBean> arrayList = new ArrayList<>();
    private MovieAdapter adapter;
    RecyclerView rvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayList = new ArrayList<>();
        bindUI();
        setAdapter();
        initInstance();
    }

    private void setAdapter() {
        adapter = new MovieAdapter(arrayList, R.layout.list_item_moview, getApplicationContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvMain.setLayoutManager(linearLayoutManager);
        rvMain.setAdapter(adapter);
    }

    private void bindUI() {
        rvMain = (RecyclerView) findViewById(R.id.rvMain);
    }

    private void initInstance() {
        if (Service.API_KEY.isEmpty()) {
            Toast.makeText(this, "Input Api Key!", Toast.LENGTH_SHORT).show();
        }

        ApiService apiService = ApiClient.getClientGoogle().create(ApiService.class);

        Call<MovieObject> call = apiService.getTopRatedMovies(Service.API_KEY);
        call.enqueue(new Callback<MovieObject>() {
            @Override
            public void onResponse(Call<MovieObject> call, Response<MovieObject> response) {
                if (response.isSuccessful()) {
                    arrayList.addAll(response.body().getResults());
                    adapter.notifyDataSetChanged();
                    Log.e("res", "onResponse: " );
                }
            }

            @Override
            public void onFailure(Call<MovieObject> call, Throwable t) {
                Log.e("onFailure", "onFailure: "+t.toString());
            }
        });

    }
}

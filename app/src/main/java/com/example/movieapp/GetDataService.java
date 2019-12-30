package com.example.movieapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetDataService {

    @GET("/photos")
    Call<List<RetroPhoto>> getAllPhotos();

    @GET("https://jsonplaceholder.typicode.com/photos/{id}")
    Call<RetroPhoto> getSinglePhoto(@Path("id") int pathId);

}

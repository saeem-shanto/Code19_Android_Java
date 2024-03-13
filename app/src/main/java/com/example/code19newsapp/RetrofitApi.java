package com.example.code19newsapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitApi {
    @GET("v2/top-headlines?country=us&category=business&apiKey=7843a06bbdf74d9eb13aa0cf97820062")
    Call<NewsApi> getNewsApi();

}

package com.mosh.trbox.di.network.main;

import com.mosh.trbox.model.response.CategoryResponse;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MainApi {

    @GET("category")
    Call<CategoryResponse> getCategory();

}

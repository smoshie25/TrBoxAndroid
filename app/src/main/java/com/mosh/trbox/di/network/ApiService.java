package com.mosh.trbox.di.network;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("basestates/")
    Call<JSONObject> getStateList(@Body JSONObject request);

}

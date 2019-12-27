package com.mosh.trbox.di.network.auth;

import com.mosh.trbox.model.request.RegisterRequest;
import com.mosh.trbox.model.response.LoginResponse;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AuthApi {

    @FormUrlEncoded
    @POST("token")
    Call<LoginResponse> login(@Field("grant_type") String grantType, @Field("username") String username,
                              @Field("password") String password);


    @POST("register")
    Call<JSONObject> register(@Body RegisterRequest request);

}

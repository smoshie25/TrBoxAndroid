package com.mosh.trbox.util;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mosh.trbox.model.response.LoginResponse;

import java.lang.reflect.Type;

import javax.inject.Inject;

public class Pref {

    private final String USER_INFO = "USER_INFO";

    @Inject
    public Pref() {
    }

    public void saveUser(Context context, LoginResponse user) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(user);
        sharedPreferencesEditor.putString(USER_INFO, json);
        sharedPreferencesEditor.apply();
    }

    public LoginResponse getUser(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String json = sharedPreferences.getString(USER_INFO, "");
        Gson gson = new Gson();
        Type type = new TypeToken<LoginResponse>() {
        }.getType();
        return gson.fromJson(json, type);
    }
}

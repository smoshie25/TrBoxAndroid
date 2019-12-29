package com.mosh.trbox.di.main;

import com.mosh.trbox.di.network.auth.AuthApi;
import com.mosh.trbox.di.network.main.MainApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MainModule {

    @Provides
    static MainApi provideMainApi(Retrofit retrofit){
        return retrofit.create(MainApi.class);
    }
}

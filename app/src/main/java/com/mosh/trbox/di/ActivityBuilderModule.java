package com.mosh.trbox.di;

import android.app.Activity;

import com.mosh.trbox.di.auth.AuthModule;
import com.mosh.trbox.di.auth.AuthViewModelModule;
import com.mosh.trbox.ui.auth.LoginActivity;
import com.mosh.trbox.ui.auth.SignUpActivity;
import com.mosh.trbox.ui.main.MainActivity;
import com.mosh.trbox.ui.splash.SplashActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(
            modules = {
                    AuthViewModelModule.class,
                    AuthModule.class
            }
    )
    abstract LoginActivity contributeLogin();

    @ContributesAndroidInjector(
            modules = {
                    AuthViewModelModule.class,
                    AuthModule.class
            }
    )
    abstract SignUpActivity contributeSignUp();

    @ContributesAndroidInjector
    abstract MainActivity contributeMain();


    @ContributesAndroidInjector
    abstract SplashActivity contributeSplash();

}

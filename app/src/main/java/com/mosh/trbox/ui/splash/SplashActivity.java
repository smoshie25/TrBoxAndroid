package com.mosh.trbox.ui.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

import com.mosh.trbox.R;
import com.mosh.trbox.util.AppCoordinator;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

import static com.mosh.trbox.util.Constants.SPLASH_TIME_OUT;

public class SplashActivity extends DaggerAppCompatActivity {

    @Inject
    AppCoordinator coordinator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                coordinator.launchLoginActivity(SplashActivity.this);
            }
        }, SPLASH_TIME_OUT);
    }
}

package com.mosh.trbox.ui.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

import com.mosh.trbox.R;
import com.mosh.trbox.model.response.LoginResponse;
import com.mosh.trbox.util.AppCoordinator;
import com.mosh.trbox.util.Pref;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

import static com.mosh.trbox.util.Constants.SPLASH_TIME_OUT;

public class SplashActivity extends DaggerAppCompatActivity {

    @Inject
    AppCoordinator coordinator;

    @Inject
    Pref pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(() -> checkLoginUser(), SPLASH_TIME_OUT);
    }

    private void checkLoginUser() {
        LoginResponse user = pref.getUser(this);
        if(user==null){
            coordinator.launchLoginActivity(this);
        }else{
            coordinator.launchMainActivity(this);
        }
    }
}

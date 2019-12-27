package com.mosh.trbox.util;

import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.mosh.trbox.ui.auth.LoginActivity;
import com.mosh.trbox.ui.auth.SignUpActivity;

import javax.inject.Inject;

public class AppCoordinator {

    @Inject
    AppCoordinator(){

    }

    public void replaceFrag(AppCompatActivity activity, int i, Fragment fragment){
        FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
        ft.replace(i,fragment);
        ft.commit();
    }

    public void launchLoginActivity(Context context){
        Intent intent = new Intent(context, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }


    public void launchSignUpActivity(Context context){
        Intent intent = new Intent(context, SignUpActivity.class);
        context.startActivity(intent);
    }


}

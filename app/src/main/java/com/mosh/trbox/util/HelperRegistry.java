package com.mosh.trbox.util;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.mosh.trbox.R;

import javax.inject.Inject;

public class HelperRegistry {
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog alertDialog;

    @Inject
    public HelperRegistry() {
    }

    public void hideSoftKeyboard(Activity activity) {
        try{
            InputMethodManager inputMethodManager =
                    (InputMethodManager) activity.getSystemService(
                            Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(
                    activity.getCurrentFocus().getWindowToken(), 0);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public boolean isEmailValid(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public boolean isEmpty(String text) {
        return TextUtils.isEmpty(text);
    }

    public boolean doStringsMatch(String s1, String s2){
        return s1.equals(s2);
    }


    public boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    public void showProgress(Activity activity, boolean show){
        if(dialogBuilder==null){
            dialogBuilder = new AlertDialog.Builder(activity);
            LayoutInflater inflater = activity.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.progress, null);
            dialogBuilder.setView(dialogView);
            alertDialog = dialogBuilder.create();
            alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            alertDialog.setCancelable(false);
        }

        if(show){
            alertDialog.show();
        }else {
            alertDialog.dismiss();
        }


    }

    public void makeToast(Context context, String message) {
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }
}

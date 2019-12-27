package com.mosh.trbox.ui.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;

import com.mosh.trbox.R;
import com.mosh.trbox.databinding.ActivitySignUpBinding;
import com.mosh.trbox.model.request.RegisterRequest;
import com.mosh.trbox.util.HelperRegistry;
import com.mosh.trbox.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class SignUpActivity extends DaggerAppCompatActivity {

    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    AuthViewModel viewModel;

    @Inject
    HelperRegistry helperRegistry;

    ActivitySignUpBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);

        viewModel = ViewModelProviders.of(this,providerFactory).get(AuthViewModel.class);

        binding.signUp.setOnClickListener(view -> attemptSubmit());
    }

    private void attemptSubmit() {
        helperRegistry.hideSoftKeyboard(this);
//Reset Error
        binding.username.setError(null);
        binding.email.setError(null);
        binding.country.setError(null);
        binding.password.setError(null);
        binding.rePassword.setError(null);

        String username = binding.username.getText().toString();
        String email = binding.email.getText().toString();
        String country = binding.country.getText().toString();
        String password = binding.password.getText().toString();
        String rePassword = binding.rePassword.getText().toString();

        boolean cancel = false;
        View focusView = null;

//Validate Data

        if (!helperRegistry.doStringsMatch(password,rePassword)) {
            binding.rePassword.setError(getString(R.string.error_re_password));
            focusView = binding.rePassword;
            cancel = true;
        }

        if (helperRegistry.isEmpty(password)) {
            binding.password.setError(getString(R.string.error_field_required));
            focusView = binding.password;
            cancel = true;
        }

        if (!helperRegistry.isEmpty(password) && !helperRegistry.isPasswordValid(password)) {
            binding.password.setError(getString(R.string.error_invalid_password));
            focusView = binding.password;
            cancel = true;
        }

        // Check for a valid email address.
        if (helperRegistry.isEmpty(country)) {
            binding.country.setError(getString(R.string.error_field_required));
            focusView = binding.country;
            cancel = true;
        }

        if (helperRegistry.isEmpty(email)) {
            binding.email.setError(getString(R.string.error_field_required));
            focusView = binding.email;
            cancel = true;
        }

        if (!helperRegistry.isEmailValid(email)) {
            binding.email.setError(getString(R.string.error_invalid_email));
            focusView = binding.email;
            cancel = true;
        }


        if (helperRegistry.isEmpty(username)) {
            binding.username.setError(getString(R.string.error_field_required));
            focusView = binding.username;
            cancel = true;
        }



        if (cancel) {
            focusView.requestFocus();
        }else {

            helperRegistry.showProgress(this, true);

            RegisterRequest request = new RegisterRequest();
            request.setUsername(username);
            request.setEmail(email);
            request.setCountry(country);
            request.setPassword(password);
            request.setConfirmpassword(rePassword);

            viewModel.register(request).observe(this,loginResponse -> {
                helperRegistry.showProgress(this,false);
                helperRegistry.makeToast(this,loginResponse.getErrorDescription());
                if(loginResponse.getErrorDescription().contains("success")){
                    finish();
                }
            });
        }
        }
}

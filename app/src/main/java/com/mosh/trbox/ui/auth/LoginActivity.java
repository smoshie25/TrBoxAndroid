package com.mosh.trbox.ui.auth;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;

import com.mosh.trbox.R;
import com.mosh.trbox.databinding.ActivityLoginBinding;
import com.mosh.trbox.util.AppCoordinator;
import com.mosh.trbox.util.HelperRegistry;
import com.mosh.trbox.util.Pref;
import com.mosh.trbox.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class LoginActivity extends DaggerAppCompatActivity {

    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    AuthViewModel viewModel;

    @Inject
    HelperRegistry helperRegistry;

    @Inject
    AppCoordinator coordinator;

    @Inject
    Pref pref;

    ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        viewModel = ViewModelProviders.of(this,providerFactory).get(AuthViewModel.class);

        binding.login.setOnClickListener(view -> attemptSubmit());

        binding.signUp.setOnClickListener(view -> coordinator.launchSignUpActivity(this));
    }

    private void attemptSubmit() {
        helperRegistry.hideSoftKeyboard(this);
//Reset Error
        binding.username.setError(null);
        binding.password.setError(null);

        String username = binding.username.getText().toString();
        String password = binding.password.getText().toString();

        boolean cancel = false;
        View focusView = null;

//Validate Data
        if (!helperRegistry.isEmpty(password) && !helperRegistry.isPasswordValid(password)) {
            binding.password.setError(getString(R.string.error_invalid_password));
            focusView = binding.password;
            cancel = true;
        }

        // Check for a valid email address.
        if (helperRegistry.isEmpty(username)) {
            binding.username.setError(getString(R.string.error_field_required));
            focusView = binding.username;
            cancel = true;
        }

        if (helperRegistry.isEmpty(password)) {
            binding.password.setError(getString(R.string.error_field_required));
            focusView = binding.password;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        }else{

            helperRegistry.showProgress(this,true);

            viewModel.login(username,password).observe(this,loginResponse -> {
                helperRegistry.showProgress(this,false);
                if(loginResponse.getErrorDescription()!=null){
                    helperRegistry.makeToast(this,loginResponse.getErrorDescription());
                }else{
                    pref.saveUser(this, loginResponse);
                }
            });
        }

    }
}

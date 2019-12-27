package com.mosh.trbox.di.auth;

import androidx.lifecycle.ViewModel;

import com.mosh.trbox.di.ViewModelKey;
import com.mosh.trbox.ui.auth.AuthViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class AuthViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel.class)
    public abstract ViewModel bindAuthViewModel(AuthViewModel viewModel);
}

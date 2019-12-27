package com.mosh.trbox.di;

import androidx.lifecycle.ViewModelProvider;

import com.mosh.trbox.viewmodels.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelFactoryModule {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModel(ViewModelProviderFactory factory);
}

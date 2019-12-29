package com.mosh.trbox.di.main;

import androidx.lifecycle.ViewModel;

import com.mosh.trbox.di.ViewModelKey;
import com.mosh.trbox.ui.main.MainViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    public abstract ViewModel bindMainViewModel(MainViewModel mainViewModel);
}

package com.mosh.trbox.di.main;

import com.mosh.trbox.ui.main.music.MusicFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBuilderModule {

    @ContributesAndroidInjector
    public abstract MusicFragment contributeMusicFrag();
}

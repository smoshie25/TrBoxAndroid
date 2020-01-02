package com.mosh.trbox.di.main;

import com.mosh.trbox.ui.main.booking.ArtistBookingDetailsFragment;
import com.mosh.trbox.ui.main.booking.ArtistSongFragment;
import com.mosh.trbox.ui.main.booking.ArtistVideoFragment;
import com.mosh.trbox.ui.main.booking.BookingFragment;
import com.mosh.trbox.ui.main.music.MusicFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBuilderModule {

    @ContributesAndroidInjector
    public abstract MusicFragment contributeMusicFrag();

    @ContributesAndroidInjector
    public abstract BookingFragment contributeBookingFrag();

    @ContributesAndroidInjector
    public abstract ArtistBookingDetailsFragment contributeArtistBookingDetailsFragment();

    @ContributesAndroidInjector
    public abstract ArtistSongFragment contributeArtistSongFragment();

    @ContributesAndroidInjector
    public abstract ArtistVideoFragment contributeArtistVideoFragment();
}

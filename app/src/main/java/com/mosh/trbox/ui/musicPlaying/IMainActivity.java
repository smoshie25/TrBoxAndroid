package com.mosh.trbox.ui.musicPlaying;

import android.support.v4.media.MediaMetadataCompat;

import com.mosh.trbox.BaseApplication;
import com.mosh.trbox.util.MyPreferenceManager;

public interface IMainActivity {

    void hideProgressBar();

    void showPrgressBar();

    void next();

    void previous();

    void onCategorySelected(String category);

    //void onArtistSelected(String category, Artist artist);

    void setActionBarTitle(String title);

    void playPause();

    BaseApplication getMyApplicationInstance();

    void onMediaSelected(String playlistId, MediaMetadataCompat mediaItem, int position);

    MyPreferenceManager getMyPreferenceManager();
}

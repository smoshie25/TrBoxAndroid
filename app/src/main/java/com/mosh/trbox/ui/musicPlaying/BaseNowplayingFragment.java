/*
 * Copyright (C) 2015 Naman Dwivedi
 *
 * Licensed under the GNU General Public License v3
 *
 * This is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 */

package com.mosh.trbox.ui.musicPlaying;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.media.MediaMetadataCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.percentlayout.widget.PercentRelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mosh.trbox.BaseApplication;
import com.mosh.trbox.R;
import com.mosh.trbox.client.MediaBrowserHelper;
import com.mosh.trbox.services.MediaService;
import com.mosh.trbox.ui.widget.PlayPauseDrawable;
import com.mosh.trbox.util.ImageUtils;
import com.mosh.trbox.util.MediaSeekBar;
import com.mosh.trbox.util.MyPreferenceManager;
import com.wang.avi.AVLoadingIndicatorView;

import net.steamcrafted.materialiconlib.MaterialIconView;


public class BaseNowplayingFragment extends Fragment {

    ImageView albumart;
    ImageView shuffle;
    ImageView playQue;
    ImageView repeat;
    PercentRelativeLayout mTim3PercentRelative;
    LinearLayout mTim3LinearLyout;
    PercentRelativeLayout mTim2PercentRelative;
    FloatingActionButton showLyricsBtn;
    boolean isPlayingQue = false;


    MaterialIconView previous, next;

    PlayPauseDrawable playPauseDrawable = new PlayPauseDrawable();
    FloatingActionButton playPauseFloating;
    View playPauseWrapper;

    String ateKey;
    int accentColor;

    TextView songtitle, songalbum, songartist, songduration, elapsedtime;
    private MediaSeekBar mSeekBarAudio;

    //For lyrics
    TextView lyricsStatus;
    AVLoadingIndicatorView lyricsLoadingIndicator;
    private RelativeLayout lyricsContainer;
    private FrameLayout lin_tim1;
    private ImageView lyricsIcon;
    private TextView lyricsContent;
    public boolean isLyricsVisisble = false;


    RecyclerView recyclerView;
    RecyclerView recyclerViewTim3;
    TextView hourColon;
    int[] timeArr = new int[]{0, 0, 0, 0, 0};

    private boolean duetoplaypause = false;

    private final OnClickListener mFLoatingButtonListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            duetoplaypause = true;
            playPauseDrawable.transformToPlay(true);
            playPauseDrawable.transformToPause(true);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mIMainActivity.playPause();

                }
            }, 250);


        }
    };
    public int accentColorTest;
//    private PreferenUtil mPreferenUtil;
    private MediaBrowserHelper mMediaBrowserHelper;
    private BaseApplication mMyApplication;
    private MyPreferenceManager mMyPrefManager;
    private ImageView mBlurredArt;
    private boolean mIsPlaying;
    private IMainActivity mIMainActivity;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarTranslucent(true);
   //     mPreferenUtil = PreferenUtil.getInstant(getActivity());
//      mMediaBrowserHelper = new MediaBrowserHelper(getContext(), MediaService.class);
//      mMediaBrowserHelper.setMediaBrowserHelperCallback(this);
        mMyApplication = BaseApplication.getInstance();
        mMyPrefManager = new MyPreferenceManager(getContext());
        //     accentColor = Config.accentColor(getActivity(), ateKey);
    }

    //For transparent header of colorprimary dark
    protected void setStatusBarTranslucent(boolean makeTranslucent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getActivity().getWindow(); // in Activity's onCreate() for instance
            w.setFlags(LayoutParams.FLAG_FULLSCREEN, LayoutParams.FLAG_FULLSCREEN);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.fragment_timber3, container, false);
        mBlurredArt = rootView.findViewById(R.id.album_art_blurred);
        albumart = rootView.findViewById(R.id.album_art);

        setMusicStateListener();
        setSongDetails(rootView);

        return rootView;
    }


    public void setSongDetails(View view) {

        albumart = view.findViewById(R.id.album_art);
        shuffle = view.findViewById(R.id.shuffle);
        playQue = view.findViewById(R.id.play_que);
        mTim3PercentRelative = view.findViewById(R.id.perc_tim3);
        mTim3LinearLyout = view.findViewById(R.id.tim3_rec_layout);
        repeat = view.findViewById(R.id.repeat);
        next = view.findViewById(R.id.next);
        previous = view.findViewById(R.id.previous);
        playPauseFloating = view.findViewById(R.id.playpausefloating);
        lyricsContent = view.findViewById(R.id.lyrics_content);
        mBlurredArt = view.findViewById(R.id.album_art_blurred);
        songtitle = view.findViewById(R.id.song_title);
        songalbum = view.findViewById(R.id.song_album);
        songartist = view.findViewById(R.id.song_artist);
        // songartist.setMovementMethod(new ScrollingMovementMethod());
        //songtitle.setMovementMethod(new ScrollingMovementMethod());
        songtitle.setSelected(true);
        songartist.setSelected(true);
        songduration = view.findViewById(R.id.song_duration);
        elapsedtime = view.findViewById(R.id.song_elapsed_time);


        mSeekBarAudio = view.findViewById(R.id.song_progress);
        recyclerViewTim3 = view.findViewById(R.id.queue_recyclerview3);
        lyricsContainer = view.findViewById(R.id.lyrics_container);
        lyricsIcon = view.findViewById(R.id.lyrics_icon);
        lyricsContent = view.findViewById(R.id.lyrics_content);
        lyricsLoadingIndicator = view.findViewById(R.id.lyrics_loading_indicator);
        lyricsStatus = view.findViewById(R.id.lyrics_status_text);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        if (toolbar != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
            final ActionBar ab = ((AppCompatActivity) getActivity()).getSupportActionBar();
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setTitle("");
        }


        if (playPauseFloating != null) {
            playPauseDrawable.setColorFilter(getContext().getResources().getColor(R.color.colorAccent), PorterDuff.Mode.MULTIPLY);
            playPauseFloating.setImageDrawable(playPauseDrawable);
        }


        setSongDetails();

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void setSongDetails() {

        updateSongDetails(mMyApplication.getMediaItem(getMyPreferenceManager().getLastPlayedMedia()));

        if (recyclerViewTim3 != null) {
            //  setQueueTim3Songs();
        }

        if (next != null) {
            next.setOnClickListener(view -> {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mIMainActivity.next();
//            MusicPlayer.next();
//              notifyPlayingDrawableChange();
                    }
                }, 200);

            });
        }
        if (previous != null) {
            previous.setOnClickListener(view -> {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                     mIMainActivity.previous();
//              MusicPlayer.previous(getActivity(), false);
//              notifyPlayingDrawableChange();
                    }
                }, 200);

            });
        }

        ///for playList on music player
        if (playQue != null) {
            playQue.setOnClickListener(v -> {
                if (isPlayingQue) {
                    isPlayingQue = false;
                    mTim3LinearLyout.setVisibility(View.GONE);
                    mTim3PercentRelative.setVisibility(View.VISIBLE);
                } else {
                    isPlayingQue = true;
                    mTim3PercentRelative.setVisibility(View.GONE);
                    mTim3LinearLyout.setVisibility(View.VISIBLE);
                    if (recyclerViewTim3 != null) {
//              setQueueTim3Songs();
                    }
                }

            });
        }


        if (playPauseFloating != null) {
            playPauseFloating.setOnClickListener(mFLoatingButtonListener);
        }

    }


    public void updateSongDetails(MediaMetadataCompat mediaItem) {
        //do not reload image if it was a play/pause change
        if (!duetoplaypause) {

        }
        duetoplaypause = false;
        if (mBlurredArt != null) {
            Glide.with(this)
                    .asBitmap()
                    .load(mediaItem.getDescription().getIconUri())
                    .into(new CustomTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            doAlbumArtStuff(resource);
                        }

                        @Override
                        public void onLoadCleared(@Nullable Drawable placeholder) {
                        }
                    });

        }

//    if (playPauseFloating != null) {
//      updatePlayPauseFloatingButton();
//    }


        setMediaTitle(mediaItem);


        if (albumart != null) {
            albumart.setVisibility(View.VISIBLE);
        }

    }


    public void setMediaTitle(MediaMetadataCompat mediaItem) {
        if (songtitle != null) {
            CharSequence aa = mediaItem.getDescription().getTitle();
            songtitle.setText(mediaItem.getDescription().getTitle());
        }

        if (songalbum != null) {
            CharSequence aas = mediaItem.getDescription().getDescription();
            songalbum.setText(mediaItem.getDescription().getSubtitle());
        }

        if (songartist != null) {
            songartist.setText(mediaItem.getDescription().getSubtitle());
        }

        Glide.with(getActivity())
                .load(mediaItem.getDescription().getIconUri())
                .into(albumart);

    }

    public void setIsPlaying(boolean isPlaying) {
        if (playPauseFloating != null) {
            if (isPlaying) {
                playPauseDrawable.transformToPause(false);
            } else {
                playPauseDrawable.transformToPlay(false);
            }
            mIsPlaying = isPlaying;
        }

    }

    public void updatePlayPauseFloatingButton() {
        if (MediaService.isSongPlaying()) {
            playPauseDrawable.transformToPause(false);
        } else {
            playPauseDrawable.transformToPlay(false);
        }
    }


    public void setMusicStateListener() {
        // ((BaseActivity) getActivity()).setMusicStateListenerListener(this);
    }



    public MediaSeekBar getMediaSeekBar() {
        return mSeekBarAudio;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mIMainActivity = (IMainActivity) getActivity();
    }


    public MyPreferenceManager getMyPreferenceManager() {
        return mMyPrefManager;
    }

    public void doAlbumArtStuff(Bitmap loadedImage) {
        setBlurredAlbumArt blurredAlbumArt = new setBlurredAlbumArt();
        blurredAlbumArt.execute(loadedImage);
    }


    private class setBlurredAlbumArt extends AsyncTask<Bitmap, Void, Drawable> {

        @Override
        protected Drawable doInBackground(Bitmap... loadedImage) {
            Drawable drawable = null;
            try {
                //        drawable = ImageUtils.blur(getContext(), loadedImage[0]);
                drawable = ImageUtils.createBlurredImageFromBitmap(loadedImage[0], getActivity(), 4);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return drawable;
        }

        @Override
        protected void onPostExecute(Drawable result) {
            if (result != null) {
                if (mBlurredArt.getDrawable() != null) {
                    final TransitionDrawable td =
                            new TransitionDrawable(new Drawable[]{
                                    mBlurredArt.getDrawable(),
                                    result
                            });
                    mBlurredArt.setImageDrawable(td);
                    td.startTransition(200);

                } else {
                    mBlurredArt.setImageDrawable(result);
                }
            }
        }

        @Override
        protected void onPreExecute() {
        }
    }

}

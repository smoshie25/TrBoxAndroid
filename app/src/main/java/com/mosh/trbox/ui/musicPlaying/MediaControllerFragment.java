package com.mosh.trbox.ui.musicPlaying;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.media.MediaMetadataCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.mosh.trbox.R;
import com.mosh.trbox.databinding.FragmentMediaControllerBinding;
import com.mosh.trbox.util.MediaSeekBar;


public class MediaControllerFragment extends Fragment implements
        View.OnClickListener
{


    private static final String TAG = "MediaControllerFragment";


    // Vars
    private IMainActivity mIMainActivity;
    private MediaMetadataCompat mSelectedMedia;
    private boolean mIsPlaying;
    private FragmentMediaControllerBinding binding;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_media_controller, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding.viewQuick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(getActivity(), NowPlayingActivity.class);
                startActivity(intent);
            }
        });
        binding.playPause.setOnClickListener(this);

        if(savedInstanceState != null){
            mSelectedMedia = savedInstanceState.getParcelable("selected_media");
            if(mSelectedMedia != null){
                setMediaTitle(mSelectedMedia);
                setIsPlaying(savedInstanceState.getBoolean("is_playing"));
            }
        }
    }

    public MediaSeekBar getMediaSeekBar(){
        return binding.seekbarAudio;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.play_pause){
            mIMainActivity.playPause();
        }
    }

    public void setIsPlaying(boolean isPlaying){
        if(isPlaying){
            Glide.with(getActivity())
                    .load(R.drawable.ic_pause_circle_outline_white_24dp)
                    .into(binding.playPause);
        }
        else{
            Glide.with(getActivity())
                    .load(R.drawable.ic_play_circle_outline_white_24dp)
                    .into(binding.playPause);
        }
        mIsPlaying = isPlaying;
    }

    public void setMediaTitle(MediaMetadataCompat mediaItem){
        mSelectedMedia = mediaItem;
        binding.mediaSongTitle.setText(mediaItem.getDescription().getTitle());
        binding.mediaSongArtist.setText(mediaItem.getDescription().getSubtitle());
        Glide.with(getContext())
                .load(mediaItem.getDescription().getIconUri())
                .into(binding.imgArtist);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mIMainActivity = (IMainActivity) getActivity();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("selected_media", mSelectedMedia);
        outState.putBoolean("is_playing", mIsPlaying);
    }
}


































package com.mosh.trbox.ui.musicPlaying;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.media.MediaMetadataCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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


    // UI Components
    private TextView mSongTitle,mSongArtist;
    private ImageView mPlayPause;
    private MediaSeekBar mSeekBarAudio;


    // Vars
    private IMainActivity mIMainActivity;
    private MediaMetadataCompat mSelectedMedia;
    private boolean mIsPlaying;
    private ImageView mSongImage;
    private LinearLayout linearLayout;
    private FragmentMediaControllerBinding binding;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_booking, container, false);

        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mSongTitle = view.findViewById(R.id.media_song_title);
        mSongArtist = view.findViewById(R.id.media_song_artist);
        mPlayPause = view.findViewById(R.id.play_pause);
        mSongImage = view.findViewById(R.id.img_artist);
        mSeekBarAudio = view.findViewById(R.id.seekbar_audio);
        linearLayout = view.findViewById(R.id.view_quick);
        binding.viewQuick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//               Intent intent = new Intent(getActivity(), NowPlayingTestActivity.class);
//                startActivity(intent);
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


































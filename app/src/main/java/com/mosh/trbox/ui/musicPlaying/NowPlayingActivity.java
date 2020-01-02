package com.mosh.trbox.ui.musicPlaying;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


import com.mosh.trbox.BaseApplication;
import com.mosh.trbox.R;
import com.mosh.trbox.client.MediaBrowserHelper;
import com.mosh.trbox.client.MediaBrowserHelperCallback;
import com.mosh.trbox.model.NameTest;
import com.mosh.trbox.services.MediaService;
import com.mosh.trbox.util.MyPreferenceManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.mosh.trbox.util.Constants.MEDIA_QUEUE_POSITION;
import static com.mosh.trbox.util.Constants.QUEUE_NEW_PLAYLIST;
import static com.mosh.trbox.util.Constants.SEEK_BAR_MAX;
import static com.mosh.trbox.util.Constants.SEEK_BAR_PROGRESS;

public class NowPlayingActivity extends AppCompatActivity implements IMainActivity, MediaBrowserHelperCallback {

    private SeekBarBroadcastReceiver mSeekbarBroadcastReceiver;
    private BaseApplication mMyApplication;
    private MyPreferenceManager mMyPrefManager;
    private MediaBrowserHelper mMediaBrowserHelper;
    private UpdateUIBroadcastReceiver mUpdateUIBroadcastReceiver;
    private static final String TAG = "TestGenresActivity";
    private boolean mIsPlaying;
    public static boolean mOnAppOpen;
    private boolean mWasConfigurationChange = false;
    private List<NameTest> tt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nowplaying);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = new BaseNowplayingFragment();
        fragmentManager.beginTransaction()
                .add(R.id.container, fragment).commit();


        mMyApplication = BaseApplication.getInstance();
        mMyPrefManager = new MyPreferenceManager(this);

        mMediaBrowserHelper = new MediaBrowserHelper(this, MediaService.class);
        mMediaBrowserHelper.setMediaBrowserHelperCallback(this);
    }

    private BaseNowplayingFragment getNowPlayingControllerFragment(){
        BaseNowplayingFragment mediaControllerFragment = (BaseNowplayingFragment)getSupportFragmentManager()
                .findFragmentById(R.id.container);
        if(mediaControllerFragment != null){
            return mediaControllerFragment;
        }
        return null;
    }

    @Override
    public void onMetadataChanged(MediaMetadataCompat metadata) {
        Log.d(TAG, "onMetadataChanged: called");
        if(metadata == null){
            return;
        }

        Objects.requireNonNull(getNowPlayingControllerFragment()).updateSongDetails(metadata);

        // Do stuff with new Metadata
   //     getNowPlayingControllerFragment().setMediaTitle(metadata);
    }

    @Override
    public void onPlaybackStateChanged(PlaybackStateCompat state) {
        Log.d(TAG, "onPlaybackStateChanged: called.");
        mIsPlaying = state != null &&
                state.getState() == PlaybackStateCompat.STATE_PLAYING;

        // update UI
        if(getNowPlayingControllerFragment() != null){
            getNowPlayingControllerFragment().setIsPlaying(mIsPlaying);
        }
    }

    @Override
    public void onMediaControllerConnected(MediaControllerCompat mediaController) {
        getNowPlayingControllerFragment().getMediaSeekBar().setMediaController(mediaController);
    }


    @Override
    public void playPause() {
        if(mOnAppOpen){
            if (mIsPlaying) {
                mMediaBrowserHelper.getTransportControls().pause();
            }
            else {
                mMediaBrowserHelper.getTransportControls().play();
            }
        }
        else{
            if(!getMyPreferenceManager().getPlaylistId().equals("")){
                onMediaSelected(
                        getMyPreferenceManager().getPlaylistId(),
                        mMyApplication.getMediaItem(getMyPreferenceManager().getLastPlayedMedia()),
                        getMyPreferenceManager().getQueuePosition()
                );
            }
            else{
                Toast.makeText(this, "select something to play", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onMediaSelected(String playlistId, MediaMetadataCompat mediaItem, int queuePosition) {
        if(mediaItem != null){
            Log.d(TAG, "onMediaSelected: CALLED: " + mediaItem.getDescription().getMediaId());

            String currentPlaylistId = getMyPreferenceManager().getPlaylistId();

            Bundle bundle = new Bundle();
            bundle.putInt(MEDIA_QUEUE_POSITION, queuePosition);
            if(playlistId.equals(currentPlaylistId)){
                mMediaBrowserHelper.getTransportControls().playFromMediaId(mediaItem.getDescription().getMediaId(), bundle);
            }
            else{
                bundle.putBoolean(QUEUE_NEW_PLAYLIST, true); // let the player know this is a new playlist
                mMediaBrowserHelper.subscribeToNewPlaylist(currentPlaylistId, playlistId);
                mMediaBrowserHelper.getTransportControls().playFromMediaId(mediaItem.getDescription().getMediaId(), bundle);
            }

            mOnAppOpen = true;
        }
        else{
            Toast.makeText(this, "select something to play", Toast.LENGTH_SHORT).show();
        }
    }

    private void addToMediaList() {
        setUp();
        final List<MediaMetadataCompat> mediaItems = new ArrayList<>();
        for (int i = 0; i < tt.size() ; i++) {
            NameTest model = tt.get(i);
            MediaMetadataCompat media = new MediaMetadataCompat.Builder()
                    .putString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID, model.getMedia_id())
                    .putString(MediaMetadataCompat.METADATA_KEY_ARTIST, model.getArtist())
                    .putString(MediaMetadataCompat.METADATA_KEY_TITLE, model.getTitle())
                    .putString(MediaMetadataCompat.METADATA_KEY_MEDIA_URI, model.getMedia_url())
                    .putString(MediaMetadataCompat.METADATA_KEY_DISPLAY_DESCRIPTION, model.getDescription())
                    .putString(MediaMetadataCompat.METADATA_KEY_DATE, model.getDate_added())
                    .putString(MediaMetadataCompat.METADATA_KEY_DISPLAY_ICON_URI, model.getImage())
                    .build();


            mediaItems.add(media);
        }

        onFinishedGettingPreviousSessionData(mediaItems);

    }

    private void  setUp(){
        tt = new ArrayList<>();
        NameTest nameTest = new NameTest("9q7XTwxuiEEobrBDedmT","http://www.jazzpodcast.nl/ATJ_181127_part2.mp3",
                "All That Jazzz","22-34-10","mY song of all time","Nov 27 episode of All That Jazz","https://www.google.com/url?sa=i&rct=j&q=&esrc=s&source=images&cd=&cad=rja&uact=8&ved=2ahUKEwinrte9ysPiAhUJXRoKHRh5DYUQjRx6BAgBEAU&url=%2Furl%3Fsa%3Di%26rct%3Dj%26q%3D%26esrc%3Ds%26source%3Dimages%26cd%3D%26ved%3D%26url%3Dhttps%253A%252F%252Fwww.real.com%252Fresources%252Fstreaming-mp4-music-videos%252F%26psig%3DAOvVaw1ZAV8T8bjYCKCnJ_tns-GR%26ust%3D1559316951452935&psig=AOvVaw1ZAV8T8bjYCKCnJ_tns-GR&ust=1559316951452935");
        NameTest nameTest1 = new NameTest("isNjgXYAyZie7QXmfSHm","http://www.jazzpodcast.nl/ATJ_181204_part2.mp3",
                "All That Jazzz","223-566-89","lOVE US BY ME","Dec 4 episode of All That Jazzz","https://www.google.com/url?sa=i&rct=j&q=&esrc=s&source=images&cd=&ved=2ahUKEwi3i7Kky8PiAhVIExoKHXAjBcQQjRx6BAgBEAU&url=http%3A%2F%2Fwallpaperswide.com%2Fmusic_14-wallpapers.html&psig=AOvVaw1ZAV8T8bjYCKCnJ_tns-GR&ust=1559316951452935");

        tt.add(nameTest);
        tt.add(nameTest1);
    }


    private void onFinishedGettingPreviousSessionData(List<MediaMetadataCompat> mediaItems){
        mMyApplication.setMediaItems(mediaItems);
        mMediaBrowserHelper.onStart(mWasConfigurationChange);
        hideProgressBar();
    }




    private class SeekBarBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            long seekProgress = intent.getLongExtra(SEEK_BAR_PROGRESS, 0);
            long seekMax = intent.getLongExtra(SEEK_BAR_MAX, 0);
            if(!getNowPlayingControllerFragment().getMediaSeekBar().isTracking()){
                getNowPlayingControllerFragment().getMediaSeekBar().setProgress((int)seekProgress);
                getNowPlayingControllerFragment().getMediaSeekBar().setMax((int)seekMax);
            }
        }
    }

    private void initSeekBarBroadcastReceiver(){
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(getString(R.string.broadcast_seekbar_update));
        mSeekbarBroadcastReceiver = new SeekBarBroadcastReceiver();
        registerReceiver(mSeekbarBroadcastReceiver, intentFilter);
    }



    @Override
    protected void onPause() {
        super.onPause();
        if(mSeekbarBroadcastReceiver != null){
            unregisterReceiver(mSeekbarBroadcastReceiver);
        }
        if(mUpdateUIBroadcastReceiver != null){
            unregisterReceiver(mUpdateUIBroadcastReceiver);
        }
    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void showPrgressBar() {

    }

    @Override
    public void next() {
        mMediaBrowserHelper.getTransportControls().skipToNext();
    }

    @Override
    public void previous() {
        mMediaBrowserHelper.getTransportControls().skipToPrevious();
    }

    @Override
    public void onCategorySelected(String category) {

    }

    @Override
    public void setActionBarTitle(String title) {

    }


    @Override
    public BaseApplication getMyApplicationInstance() {
        return mMyApplication;
    }

    @Override
    public MyPreferenceManager getMyPreferenceManager() {
        return mMyPrefManager;
    }

    @Override
    protected void onResume() {
        super.onResume();
        initSeekBarBroadcastReceiver();
        initUpdateUIBroadcastReceiver();
    }

    private class UpdateUIBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String newMediaId = intent.getStringExtra(getString(R.string.broadcast_new_media_id));
//            Log.d("OnRecieve", "onReceive: CALLED: " + newMediaId);
//            if(getPlaylistFragment() != null){
//                Log.d(TAG, "onReceive: " + mMyApplication.getMediaItem(newMediaId).getDescription().getMediaId());
//                getPlaylistFragment().updateUI(mMyApplication.getMediaItem(newMediaId));
//            }
        }
    }
    private void initUpdateUIBroadcastReceiver(){
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(getString(R.string.broadcast_update_ui));
        mUpdateUIBroadcastReceiver = new UpdateUIBroadcastReceiver();
        registerReceiver(mUpdateUIBroadcastReceiver, intentFilter);
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mWasConfigurationChange = true;
    }

    @Override
    public void onStart() {
        super.onStart();

        if(!getMyPreferenceManager().getPlaylistId().equals("")){
            String aa = "";
            mMediaBrowserHelper.onStart(mWasConfigurationChange);
          //  addToMediaList();
        }
        else{
            mMediaBrowserHelper.onStart(mWasConfigurationChange);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: called.");
        mMediaBrowserHelper.onStop();
        getNowPlayingControllerFragment().getMediaSeekBar().disconnectController();

    }


}

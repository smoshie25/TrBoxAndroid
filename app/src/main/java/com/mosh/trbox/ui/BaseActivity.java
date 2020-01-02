package com.mosh.trbox.ui;

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

import com.mosh.trbox.BaseApplication;
import com.mosh.trbox.R;
import com.mosh.trbox.client.MediaBrowserHelper;
import com.mosh.trbox.client.MediaBrowserHelperCallback;
import com.mosh.trbox.model.NameTest;
import com.mosh.trbox.services.MediaService;
import com.mosh.trbox.ui.musicPlaying.IMainActivity;
import com.mosh.trbox.ui.musicPlaying.MediaControllerFragment;
import com.mosh.trbox.util.MyPreferenceManager;

import java.util.ArrayList;
import java.util.List;

import dagger.android.support.DaggerAppCompatActivity;

import static com.mosh.trbox.util.Constants.MEDIA_QUEUE_POSITION;
import static com.mosh.trbox.util.Constants.QUEUE_NEW_PLAYLIST;
import static com.mosh.trbox.util.Constants.SEEK_BAR_MAX;
import static com.mosh.trbox.util.Constants.SEEK_BAR_PROGRESS;


public class BaseActivity extends DaggerAppCompatActivity implements IMainActivity, MediaBrowserHelperCallback {

    private SeekBarBroadcastReceiver mSeekbarBroadcastReceiver;
    public BaseApplication mMyApplication;
    public MyPreferenceManager mMyPrefManager;
    public MediaBrowserHelper mMediaBrowserHelper;
    private UpdateUIBroadcastReceiver mUpdateUIBroadcastReceiver;
    public static final String TAG = "TestGenresActivity";
    public boolean mIsPlaying;
    public static boolean mOnAppOpen;
    public boolean mWasConfigurationChange = false;
    private List<NameTest> tt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMyApplication = BaseApplication.getInstance();
        mMyPrefManager = new MyPreferenceManager(this);

        mMediaBrowserHelper = new MediaBrowserHelper(this, MediaService.class);
        mMediaBrowserHelper.setMediaBrowserHelperCallback(this);
    }


    private MediaControllerFragment getMediaControllerFragment() {
        MediaControllerFragment mediaControllerFragment = (MediaControllerFragment) getSupportFragmentManager()
                .findFragmentById(R.id.bottom_media_controller);
        if (mediaControllerFragment != null) {
            return mediaControllerFragment;
        }
        return null;
    }

    @Override
    public void onMetadataChanged(MediaMetadataCompat metadata) {
        Log.d(TAG, "onMetadataChanged: called");
        if (metadata == null) {
            return;
        }

        // Do stuff with new Metadata
        if (getMediaControllerFragment() != null) {
            getMediaControllerFragment().setMediaTitle(metadata);
        }
    }

    @Override
    public void onPlaybackStateChanged(PlaybackStateCompat state) {
        Log.d(TAG, "onPlaybackStateChanged: called.");
        mIsPlaying = state != null &&
                state.getState() == PlaybackStateCompat.STATE_PLAYING;

        // update UI
        if (getMediaControllerFragment() != null) {
            getMediaControllerFragment().setIsPlaying(mIsPlaying);
        }
    }

    @Override
    public void onMediaControllerConnected(MediaControllerCompat mediaController) {
        if (getMediaControllerFragment() != null) {
            getMediaControllerFragment().getMediaSeekBar().setMediaController(mediaController);
        }
    }


    @Override
    public void playPause() {
        if (mOnAppOpen) {
            if (mIsPlaying) {
                mMediaBrowserHelper.getTransportControls().pause();
            } else {
                mMediaBrowserHelper.getTransportControls().play();
            }
        } else {
            if (!getMyPreferenceManager().getPlaylistId().equals("")) {
                onMediaSelected(
                        getMyPreferenceManager().getPlaylistId(),
                        mMyApplication.getMediaItem(getMyPreferenceManager().getLastPlayedMedia()),
                        getMyPreferenceManager().getQueuePosition()
                );
            } else {
                Toast.makeText(this, "select something to play", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onMediaSelected(String playlistId, MediaMetadataCompat mediaItem, int queuePosition) {
        if (mediaItem != null) {
            Log.d(TAG, "onMediaSelected: CALLED: " + mediaItem.getDescription().getMediaId());

            String currentPlaylistId = getMyPreferenceManager().getPlaylistId();

            Bundle bundle = new Bundle();
            bundle.putInt(MEDIA_QUEUE_POSITION, queuePosition);
            if (playlistId.equals(currentPlaylistId)) {
                mMediaBrowserHelper.getTransportControls().playFromMediaId(mediaItem.getDescription().getMediaId(), bundle);
            } else {
                bundle.putBoolean(QUEUE_NEW_PLAYLIST, true); // let the player know this is a new playlist
                mMediaBrowserHelper.subscribeToNewPlaylist(currentPlaylistId, playlistId);
                mMediaBrowserHelper.getTransportControls().playFromMediaId(mediaItem.getDescription().getMediaId(), bundle);
            }

            mOnAppOpen = true;
        } else {
            Toast.makeText(this, "select something to play", Toast.LENGTH_SHORT).show();
        }
    }

    private void addToMediaList() {
        setUp();
        final List<MediaMetadataCompat> mediaItems = new ArrayList<>();
        for (int i = 0; i < tt.size(); i++) {
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

    private void setUp() {
        tt = new ArrayList<>();
        NameTest nameTest = new NameTest("9q7XTwxuiEEobrBDedmT", "http://www.jazzpodcast.nl/ATJ_181127_part2.mp3",
                "All That Jazzz", "22-34-10", "mY song of all time", "Nov 27 episode of All That Jazz", "https://cdn.pixabay.com/photo/2015/12/01/20/28/road-1072823__340.jpg");
        NameTest nameTest1 = new NameTest("isNjgXYAyZie7QXmfSHm", "http://www.jazzpodcast.nl/ATJ_181204_part2.mp3",
                "All That Jazzz", "223-566-89", "lOVE US BY ME", "Dec 4 episode of All That Jazzz", "https://cdn.pixabay.com/photo/2017/02/08/17/24/butterfly-2049567__340.jpg");

        tt.add(nameTest);
        tt.add(nameTest1);
    }


    public void onFinishedGettingPreviousSessionData(List<MediaMetadataCompat> mediaItems) {
        mMyApplication.setMediaItems(mediaItems);
        mMediaBrowserHelper.onStart(mWasConfigurationChange);
        hideProgressBar();
    }




    private class SeekBarBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            long seekProgress = intent.getLongExtra(SEEK_BAR_PROGRESS, 0);
            long seekMax = intent.getLongExtra(SEEK_BAR_MAX, 0);
            if (!getMediaControllerFragment().getMediaSeekBar().isTracking()) {
                getMediaControllerFragment().getMediaSeekBar().setProgress((int) seekProgress);
                getMediaControllerFragment().getMediaSeekBar().setMax((int) seekMax);
            }
        }
    }

    private void initSeekBarBroadcastReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(getString(R.string.broadcast_seekbar_update));
        mSeekbarBroadcastReceiver = new SeekBarBroadcastReceiver();
        registerReceiver(mSeekbarBroadcastReceiver, intentFilter);
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (mSeekbarBroadcastReceiver != null) {
            unregisterReceiver(mSeekbarBroadcastReceiver);
        }
        if (mUpdateUIBroadcastReceiver != null) {
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

    }

    @Override
    public void previous() {

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
            Log.d("OnRecieve", "onReceive: CALLED: " + newMediaId);
//            if (getPlaylistFragment() != null) {
//                Log.d(TAG, "onReceive: " + mMyApplication.getMediaItem(newMediaId).getDescription().getMediaId());
//             //   getPlaylistFragment().updateUI(mMyApplication.getMediaItem(newMediaId));
//            }
        }
    }

    private void initUpdateUIBroadcastReceiver() {
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

        if (!getMyPreferenceManager().getPlaylistId().equals("")) {
            String aa = "";
         //  addToMediaList();
            mMediaBrowserHelper.onStart(mWasConfigurationChange);
        } else {
            mMediaBrowserHelper.onStart(mWasConfigurationChange);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: called.");
        mMediaBrowserHelper.onStop();
       getMediaControllerFragment().getMediaSeekBar().disconnectController();

    }


}

package com.mosh.trbox;

import android.app.Activity;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.util.Log;

import androidx.multidex.MultiDexApplication;

import com.mosh.trbox.di.AppInjector;
import com.mosh.trbox.di.DaggerAppComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.DaggerApplication;

public class BaseApplication extends MultiDexApplication implements HasActivityInjector {
    private List<MediaBrowserCompat.MediaItem> mMediaItems = new ArrayList<>();
    private TreeMap<String, MediaMetadataCompat> mTreeMap = new TreeMap<>();
    private static BaseApplication mInstance;
    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    public List<MediaBrowserCompat.MediaItem> getMediaItems(){
        return mMediaItems;
    }

    public TreeMap<String, MediaMetadataCompat> getTreeMap(){
        return mTreeMap;
    }

    public void setMediaItems(List<MediaMetadataCompat> mediaItems){
        mMediaItems.clear();
        for(MediaMetadataCompat item: mediaItems){
            Log.d("", "setMediaItems: called: adding media item: " + item.getDescription());
            mMediaItems.add(
                    new MediaBrowserCompat.MediaItem(
                            item.getDescription(), MediaBrowserCompat.MediaItem.FLAG_PLAYABLE)
            );
            mTreeMap.put(item.getDescription().getMediaId(), item);
        }
    }

    public MediaMetadataCompat getMediaItem(String mediaId){
        return mTreeMap.get(mediaId);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        AppInjector.init(this);
    }


    public static BaseApplication getInstance(){
        if(mInstance == null){
            mInstance = new BaseApplication();
        }
        return mInstance;
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}

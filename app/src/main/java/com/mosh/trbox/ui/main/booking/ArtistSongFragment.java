package com.mosh.trbox.ui.main.booking;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.media.MediaMetadataCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import com.mosh.trbox.R;
import com.mosh.trbox.databinding.FragmentArtistSongBinding;
import com.mosh.trbox.model.response.ArtistItem;
import com.mosh.trbox.model.response.artistdetails.SongArtist;
import com.mosh.trbox.ui.BaseActivity;
import com.mosh.trbox.ui.main.MainActivity;
import com.mosh.trbox.ui.main.MainViewModel;
import com.mosh.trbox.ui.musicPlaying.IMainActivity;
import com.mosh.trbox.util.GridMarginDecoration;
import com.mosh.trbox.viewmodels.ViewModelProviderFactory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class ArtistSongFragment extends DaggerFragment implements ArtistBookingSongRecyclerAdapter.IMediaSelector {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private List<SongArtist> songArrayList;
    private String mParam1;
    private FragmentArtistSongBinding binding;
    private SongListArtistBookingAdapter adapter;
    private int page = 1;
    private int limit = 20;
    private ArrayList<MediaMetadataCompat> mMediaList = new ArrayList<>();
    private IMainActivity mIMainActivity;
    private String mSelectedCategory;
    private ArtistItem mSelectArtist;
    private MediaMetadataCompat mSelectedMedia;
    private ArtistBookingSongRecyclerAdapter mAdapter;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    MainViewModel viewModel;

    public ArtistSongFragment() {
        // Required empty public constructor
    }


    public static ArtistSongFragment newInstance(ArtistItem artistItem) {
        ArtistSongFragment fragment = new ArtistSongFragment();
        Bundle args = new Bundle();
        args.putSerializable("ITEM", artistItem);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if (getArguments() != null) {
            mSelectedCategory = getArguments().getString("category");
            mSelectArtist =  (ArtistItem) getArguments().getSerializable("ITEM");
        }
    }


    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
         binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_artist_song, container, false);
        View view = binding.getRoot();
        //here data must be an instance of the class MarsDataProvider
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpAdapter();
        if (mSelectArtist != null){
            retrieveArtistInfo();
        }
    }

    private void setUpAdapter(){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        binding.recyclerview.setLayoutManager(gridLayoutManager);
        binding.recyclerview.addItemDecoration(new GridMarginDecoration(getContext(), 1, 1, 1, 1));
        mAdapter = new ArtistBookingSongRecyclerAdapter(getActivity(), mMediaList, this);
        binding.recyclerview.setAdapter(mAdapter);
    }

    private void getSelectedMediaItem(String mediaId){
        for(MediaMetadataCompat mediaItem: mMediaList){
            if(mediaItem.getDescription().getMediaId().equals(mediaId)){
                mSelectedMedia = mediaItem;
                mAdapter.setSelectedIndex(mAdapter.getIndexOfItem(mSelectedMedia));
                break;
            }
        }
    }

    private void retrieveArtistInfo() {
       binding.progress.setVisibility(View.VISIBLE);
        viewModel.getArtistBookingSong(page,mSelectArtist.getId()).observe(this,response -> {
            if(response.getSongs()!=null){
                getAllSongs(response.getSongs());
                binding.progress.setVisibility(View.GONE);
            }else {
                binding.progress.setVisibility(View.GONE);
            }
        });
    }

    private void getAllSongs(List<SongArtist> songArrayList){
        for (int i = 0; i < songArrayList.size() ; i++) {
            SongArtist model = songArrayList.get(i);
            MediaMetadataCompat media = new MediaMetadataCompat.Builder()
                  //  .putBitmap()
                    .putString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID, model.getSongId())
                    .putString(MediaMetadataCompat.METADATA_KEY_ARTIST, model.getArtist().getName())
                    .putString(MediaMetadataCompat.METADATA_KEY_TITLE, model.getTitle())
                    .putString(MediaMetadataCompat.METADATA_KEY_MEDIA_URI, model.getMedia())
                    .putString(MediaMetadataCompat.METADATA_KEY_DISPLAY_DESCRIPTION, model.getArtist().getProfile())
                    .putString(MediaMetadataCompat.METADATA_KEY_DATE, "")
                    .putString(MediaMetadataCompat.METADATA_KEY_DISPLAY_ICON_URI, model.getImage())
                    .build();
            mMediaList.add(media);
        }
        ((BaseActivity) getActivity()).onFinishedGettingPreviousSessionData(mMediaList);

        setUpAdapter();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mIMainActivity = (IMainActivity) getActivity();
    }

    @Override
    public void onMediaSelected(int position) {
        mIMainActivity.getMyApplicationInstance().setMediaItems(mMediaList);
        mSelectedMedia = mMediaList.get(position);
        mAdapter.setSelectedIndex(position);

        mIMainActivity.onMediaSelected(
                mSelectArtist.getId(), // playlist_id = artist_id
                mMediaList.get(position),
                position);

        saveLastPlayedSongProperties();

    }

    public void updateUI(MediaMetadataCompat mediaItem){
        mAdapter.setSelectedIndex(mAdapter.getIndexOfItem(mediaItem));
        mSelectedMedia = mediaItem;
        saveLastPlayedSongProperties();
    }

    private void saveLastPlayedSongProperties(){
        mIMainActivity.getMyPreferenceManager().savePlaylistId(mSelectArtist.getId()); // playlist id is same as artist id
        mIMainActivity.getMyPreferenceManager().saveLastPlayedArtist(mSelectArtist.getId());
        mIMainActivity.getMyPreferenceManager().saveLastPlayedCategory(mSelectedCategory);
        mIMainActivity.getMyPreferenceManager().saveLastPlayedArtistImage(mSelectArtist.getImage());
        mIMainActivity.getMyPreferenceManager().saveLastPlayedMedia(mSelectedMedia.getDescription().getMediaId());
    }
}

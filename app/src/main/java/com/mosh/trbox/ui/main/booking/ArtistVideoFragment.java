package com.mosh.trbox.ui.main.booking;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.mosh.trbox.R;
import com.mosh.trbox.databinding.FragmentArtistVideoBinding;
import com.mosh.trbox.model.response.ArtistItem;
import com.mosh.trbox.model.response.artistdetails.VideoArtist;
import com.mosh.trbox.ui.main.MainViewModel;
import com.mosh.trbox.util.EndlessRecyclerOnScrollListener;
import com.mosh.trbox.util.GridMarginDecoration;
import com.mosh.trbox.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class ArtistVideoFragment extends DaggerFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String artistId;
    private FragmentArtistVideoBinding binding;
    private VideoListArtistBookingAdapter adapter;
    private int page = 1;
    private int limit = 20;
    private GridLayoutManager gridLayoutManager;
    private EndlessRecyclerOnScrollListener endlessRecyclerOnScrollListener;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    MainViewModel viewModel;
    private ArtistItem mSelectArtist;

    public ArtistVideoFragment() {
        // Required empty public constructor
    }

    public static ArtistVideoFragment newInstance(ArtistItem artistItem) {
        ArtistVideoFragment fragment = new ArtistVideoFragment();
        Bundle args = new Bundle();
        args.putSerializable("ITEM", artistItem);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mSelectArtist =  (ArtistItem) getArguments().getSerializable("ITEM");
        }
    }


    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_artist_video, container, false);
        View view = binding.getRoot();

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
        adapter = new VideoListArtistBookingAdapter(getContext(), new VideoListArtistBookingAdapter.ClickListner() {
            @Override
            public void onItemClick(VideoArtist model, int position) {

            }
        });
        gridLayoutManager = new GridLayoutManager(getContext(), 2);
        binding.recyclerview.setLayoutManager(gridLayoutManager);
     //   binding.recyclerview.setHasFixedSize(true);

        binding.recyclerview.addItemDecoration(new GridMarginDecoration(getContext(), 1, 1, 1, 1));
   //     binding.recyclerview.setHasFixedSize(true);
        binding.recyclerview.setAdapter(adapter);

    //    addScroll();

    }

    private void retrieveArtistInfo() {
        binding.progress.setVisibility(View.VISIBLE);
        viewModel.fetchVideoOfArtistBooking(page,mSelectArtist.getId()).observe(this,response -> {
            if(response.getVideoList()!=null){
                adapter.addAll(response.getVideoList());
                binding.progress.setVisibility(View.GONE);
            }else {
                binding.progress.setVisibility(View.GONE);
            }
        });
    }

    private void addScroll() {
        endlessRecyclerOnScrollListener = new EndlessRecyclerOnScrollListener(gridLayoutManager, page, limit) {
            @Override
            public void onLoadMore(int next) {
                page = next;
                retrieveArtistInfo();
            }
        };

        binding.recyclerview.addOnScrollListener(endlessRecyclerOnScrollListener);
    }

}

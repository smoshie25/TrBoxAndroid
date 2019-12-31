package com.mosh.trbox.ui.main.music;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mosh.trbox.R;
import com.mosh.trbox.databinding.FragmentMusicBinding;
import com.mosh.trbox.model.CategoryType;
import com.mosh.trbox.model.FeedCategory;
import com.mosh.trbox.ui.auth.AuthViewModel;
import com.mosh.trbox.ui.main.MainViewModel;
import com.mosh.trbox.viewmodels.ViewModelProviderFactory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MusicFragment extends DaggerFragment {


    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    MainViewModel viewModel;

    List<FeedCategory> categoryList = new ArrayList<>();
    private MusicRecyclerViewAdapter adapter;

    public MusicFragment() {
        // Required empty public constructor
    }

    FragmentMusicBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_music, container, false);

        viewModel = ViewModelProviders.of(this,providerFactory).get(MainViewModel.class);

        binding.messagesView.setVisibility(View.GONE);
        binding.shimmerViewContainer.startShimmerAnimation();

        adapter =  new MusicRecyclerViewAdapter(categoryList);
        binding.messagesView.setAdapter(adapter);
        loadSlider();

        return binding.getRoot();
    }

    private void loadSlider(){
        FeedCategory feed = new FeedCategory(CategoryType.SLIDER,"SLIDER");

        categoryList.add(0,feed);
        adapter.notifyDataSetChanged();
        loadGenre();

    }

    private void loadCategory() {
        viewModel.getCategory().observe(this,response -> {
            if(response.getValue()!=null){
                binding.messagesView.setVisibility(View.VISIBLE);

                binding.shimmerViewContainer.stopShimmerAnimation();
                binding.shimmerViewContainer.setVisibility(View.GONE);

                FeedCategory feed = new FeedCategory(CategoryType.MENU,"Category");
                feed.setCategoryItems(response.getValue());
                categoryList.add(feed);

                adapter.notifyDataSetChanged();
                loadSongs2();
            }
        });
    }

    private void loadSongs() {
        viewModel.getSongs().observe(this,response -> {
            if(response.getValue()!=null){

                FeedCategory feed = new FeedCategory(CategoryType.SONG, "Popular");
                feed.setSongItems(response.getValue());
                categoryList.add(feed);

                adapter.notifyDataSetChanged();
                loadCategory();
            }
        });
    }

    private void loadSongs2() {
        viewModel.getSongs().observe(this,response -> {
            if(response.getValue()!=null){

                FeedCategory feed = new FeedCategory(CategoryType.SONG, "New release");
                feed.setSongItems(response.getValue());
                categoryList.add(feed);

                adapter.notifyDataSetChanged();
            }
        });
    }

    private void loadGenre() {
        viewModel.getGenre().observe(this,response -> {
            if(response.getValue()!=null){

                FeedCategory feed = new FeedCategory(CategoryType.GENRE, "Browse by Genre");
                feed.setCategoryItems(response.getValue());
                categoryList.add(feed);

                adapter.notifyDataSetChanged();

                loadSongs();
            }
        });
    }

}

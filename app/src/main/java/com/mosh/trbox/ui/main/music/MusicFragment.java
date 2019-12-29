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

        loadCategory();
        return binding.getRoot();
    }

    private void loadCategory() {
        viewModel.getCategory().observe(this,response -> {
            if(response.getValue()!=null){
                List<FeedCategory> categoryList = new ArrayList<>();
                categoryList.add(new FeedCategory(CategoryType.MENU,"Category",response.getValue()));

                binding.messagesView.setAdapter(new MusicRecyclerViewAdapter(categoryList));
            }
        });
    }

}

package com.mosh.trbox.ui.main.booking;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mosh.trbox.R;
import com.mosh.trbox.databinding.FragmentBookingBinding;
import com.mosh.trbox.model.BookingCategory;
import com.mosh.trbox.model.BookingCategoryType;
import com.mosh.trbox.model.CategoryType;
import com.mosh.trbox.model.FeedCategory;
import com.mosh.trbox.model.response.CategoryItem;
import com.mosh.trbox.ui.main.MainViewModel;
import com.mosh.trbox.util.AppCoordinator;
import com.mosh.trbox.viewmodels.ViewModelProviderFactory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookingFragment extends DaggerFragment {

    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    MainViewModel viewModel;

    @Inject
    AppCoordinator coordinator;

    private BookingRecyclerViewAdapter adapter;

    public BookingFragment() {
        // Required empty public constructor
    }

    List<BookingCategory> bookingCategoryList = new ArrayList<>();

    FragmentBookingBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_booking, container, false);

        viewModel = ViewModelProviders.of(this,providerFactory).get(MainViewModel.class);

        binding.messagesView.setVisibility(View.GONE);
        binding.shimmerViewContainer.startShimmerAnimation();

        adapter = new BookingRecyclerViewAdapter(bookingCategoryList,getContext(),coordinator);
        binding.messagesView.setAdapter(adapter);

        loadSlider();
        return binding.getRoot();
    }

    private void loadSlider(){
        BookingCategory feed = new BookingCategory(BookingCategoryType.SLIDER,"SLIDER");

        bookingCategoryList.add(0,feed);
        adapter.notifyDataSetChanged();
        loadArtist();

    }

    private void loadArtist() {
        viewModel.getCategory().observe(this,response -> {
            if(response.getValue()!=null){

                binding.messagesView.setVisibility(View.VISIBLE);

                binding.shimmerViewContainer.stopShimmerAnimation();
                binding.shimmerViewContainer.setVisibility(View.GONE);

                for(CategoryItem categoryItem : response.getValue()){
                    BookingCategory feed = new BookingCategory(BookingCategoryType.ARTIST, categoryItem.getName());
                    feed.setArtistItems(categoryItem.getArtists());
                    bookingCategoryList.add(feed);
                }



                adapter.notifyDataSetChanged();

            }
        });
    }

}

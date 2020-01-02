package com.mosh.trbox.ui.main.booking;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.mosh.trbox.R;
import com.mosh.trbox.databinding.FragmentArtistBookingDetailsBinding;
import com.mosh.trbox.model.response.ArtistItem;

public class ArtistBookingDetailsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    FragmentArtistBookingDetailsBinding binding;
    private ArtistItem artist;


    public ArtistBookingDetailsFragment() {
        // Required empty public constructor
    }

    public static ArtistBookingDetailsFragment newInstance(ArtistItem artistItem) {
        ArtistBookingDetailsFragment fragment = new ArtistBookingDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable("ITEM", artistItem);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            artist = (ArtistItem) getArguments().getSerializable("ITEM");
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpView();
        Fragment songFragment =  ArtistSongFragment.newInstance(artist);
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.child_fragment_container, songFragment).commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_artist_booking_details, container, false);
        ((AppCompatActivity) getActivity()).setSupportActionBar(binding.toolbarMain);

        final ActionBar ab = ((AppCompatActivity) getActivity()).getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle("Artist Details");
        binding.imgVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (artist != null){
                    Fragment fragment =  ArtistVideoFragment.newInstance(artist);
                    FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                    transaction.replace(R.id.child_fragment_container, fragment).commit();
                }

            }
        });

        binding.imgSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (artist != null){
                    Fragment songFragment =  ArtistSongFragment.newInstance(artist);
                    FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                    transaction.replace(R.id.child_fragment_container, songFragment).commit();
                }

            }
        });
        return binding.getRoot();
    }

    private void setUpView(){
        if (artist.getImage() != null){
            Glide.with(getContext())
                    .load(artist.getImage())
                    .into(binding.artistImage);
        }

        binding.txtArtisteName.setText(artist.getName());
        binding.txtArtisteProfile.setText(artist.getProfile());

    }

}

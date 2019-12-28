package com.mosh.trbox.ui.main.library;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mosh.trbox.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LibFragment extends Fragment {


    public LibFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lib, container, false);
    }

}

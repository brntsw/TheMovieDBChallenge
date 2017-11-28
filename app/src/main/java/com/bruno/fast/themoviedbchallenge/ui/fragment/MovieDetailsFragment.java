package com.bruno.fast.themoviedbchallenge.ui.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bruno.fast.themoviedbchallenge.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetailsFragment extends BaseFragment {

    public static final String TAG = MovieDetailsFragment.class.getName();

    public static MovieDetailsFragment newInstance(Bundle bundle){
        MovieDetailsFragment fragment = new MovieDetailsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public MovieDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie_details, container, false);

        setup(rootView);

        return rootView;
    }

    @Override
    void setup(View rootView) {

    }
}

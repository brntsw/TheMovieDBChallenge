package com.bruno.fast.themoviedbchallenge.ui.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.bruno.fast.themoviedbchallenge.R;
import com.bruno.fast.themoviedbchallenge.data.model.Genre;
import com.bruno.fast.themoviedbchallenge.presentation.GenresListContract;
import com.bruno.fast.themoviedbchallenge.presentation.GenresListPresenter;
import com.bruno.fast.themoviedbchallenge.ui.adapter.GenresAdapter;
import com.bruno.fast.themoviedbchallenge.util.CommonUtil;
import com.bruno.fast.themoviedbchallenge.util.FragmentUtil;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class GenresListFragment extends BaseFragment implements GenresListContract {

    public static final String TAG = GenresListFragment.class.getName();

    private CoordinatorLayout coordinatorLayout;
    private TextView tvNoGenres;
    private GridView gridGenres;
    private GenresAdapter adapter;
    private ProgressDialog mProgress;
    private GenresListContract.Presenter genresListPresenter;

    public static GenresListFragment newInstance(Bundle bundle){
        GenresListFragment fragment = new GenresListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public GenresListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_genres_list, container, false);

        setup(rootView);

        genresListPresenter.getGenresList();

        return rootView;
    }

    @Override
    void setup(View rootView) {
        if(getActivity() != null) {
            coordinatorLayout = getActivity().findViewById(R.id.coordinator_main);
        }
        tvNoGenres = rootView.findViewById(R.id.tv_no_genres);
        gridGenres = rootView.findViewById(R.id.grid_genres);

        genresListPresenter = new GenresListPresenter(getActivity(), this);
    }

    @Override
    public void showProgress() {
        hideProgress();
        mProgress = CommonUtil.showLoadingDialog(getActivity(), "");
    }

    @Override
    public void hideProgress() {
        if (mProgress != null && mProgress.isShowing()) {
            mProgress.cancel();
        }
    }

    @Override
    public void onSuccess(ArrayList<Genre> genres) {
        if(genres != null && genres.size() > 0) {
            adapter = new GenresAdapter(getActivity(), genres);

            gridGenres.setAdapter(adapter);

            gridGenres.setOnItemClickListener((adapterView, view, position, l) -> {
                Genre genre = (Genre) adapterView.getItemAtPosition(position);

                Bundle bundle = new Bundle();
                bundle.putInt(Genre.BUNDLE_ID, genre.getId());

                MoviesListFragment fragment = MoviesListFragment.newInstance(bundle);
                FragmentUtil.replaceFragment(getFragmentManager(), fragment, MoviesListFragment.TAG);
            });
        }
        else{
            tvNoGenres.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onError(String msg) {
        CommonUtil.showSnackbar(getActivity(), coordinatorLayout, msg, Snackbar.LENGTH_LONG);
    }
}

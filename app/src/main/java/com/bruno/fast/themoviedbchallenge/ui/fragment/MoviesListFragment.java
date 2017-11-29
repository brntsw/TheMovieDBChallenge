package com.bruno.fast.themoviedbchallenge.ui.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
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
import com.bruno.fast.themoviedbchallenge.data.model.Movie;
import com.bruno.fast.themoviedbchallenge.presentation.MoviesListContract;
import com.bruno.fast.themoviedbchallenge.presentation.MoviesListPresenter;
import com.bruno.fast.themoviedbchallenge.ui.activity.MainActivity;
import com.bruno.fast.themoviedbchallenge.ui.adapter.MoviesAdapter;
import com.bruno.fast.themoviedbchallenge.util.CommonUtil;
import com.bruno.fast.themoviedbchallenge.util.FragmentUtil;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesListFragment extends BaseFragment implements MoviesListContract {

    public static final String TAG = MoviesListFragment.class.getName();

    private CoordinatorLayout coordinatorLayout;
    private ProgressDialog mProgress;
    private Bundle args;
    private int genreId;
    private TextView tvNoMovies;
    private GridView gridMovies;
    private MoviesAdapter adapter;
    private MoviesListContract.Presenter moviesListPresenter;

    public static MoviesListFragment newInstance(Bundle args){
        MoviesListFragment fragment = new MoviesListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public MoviesListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movies_list, container, false);

        setup(rootView);

        if(getActivity() != null && getActivity() instanceof MainActivity){
            ((MainActivity)getActivity()).showBackArrow();
        }

        if(args != null){
            genreId = args.getInt(Genre.BUNDLE_ID);

            if(genreId > 0) {
                moviesListPresenter.getMoviesList(genreId);
            }
        }

        return rootView;
    }

    @Override
    void setup(View rootView) {
        args = getArguments();

        if(getActivity() != null){
            coordinatorLayout = getActivity().findViewById(R.id.coordinator_main);
            if(getActivity() instanceof MainActivity){
                ((MainActivity)getActivity()).showToolbar();
            }
        }

        tvNoMovies = rootView.findViewById(R.id.tv_no_movies);
        gridMovies = rootView.findViewById(R.id.grid_movies);

        moviesListPresenter = new MoviesListPresenter(getActivity(), this);
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
    public void onSuccess(ArrayList<Movie> movies) {
        if(movies != null && movies.size() > 0){
            adapter = new MoviesAdapter(getActivity(), movies);
            gridMovies.setAdapter(adapter);

            gridMovies.setOnItemClickListener((adapterView, view, position, l) -> {
                Movie movie = (Movie) adapterView.getItemAtPosition(position);

                Bundle bundle = new Bundle();
                bundle.putLong(Movie.BUNDLE_ID, movie.getId());

                MovieDetailsFragment fragment = MovieDetailsFragment.newInstance(bundle);
                FragmentUtil.replaceFragment(getFragmentManager(), fragment, MovieDetailsFragment.TAG);
            });
        }
        else{
            tvNoMovies.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onError(String msg) {
        CommonUtil.showSnackbar(getActivity(), coordinatorLayout, msg, Snackbar.LENGTH_LONG);
    }
}

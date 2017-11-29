package com.bruno.fast.themoviedbchallenge.ui.fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bruno.fast.themoviedbchallenge.R;
import com.bruno.fast.themoviedbchallenge.data.model.Genre;
import com.bruno.fast.themoviedbchallenge.data.model.Movie;
import com.bruno.fast.themoviedbchallenge.presentation.MovieDetailsContract;
import com.bruno.fast.themoviedbchallenge.presentation.MovieDetailsPresenter;
import com.bruno.fast.themoviedbchallenge.ui.activity.MainActivity;
import com.bruno.fast.themoviedbchallenge.util.AppConstants;
import com.bruno.fast.themoviedbchallenge.util.CommonUtil;
import com.bruno.fast.themoviedbchallenge.util.FragmentUtil;
import com.bruno.fast.themoviedbchallenge.util.LanguageUtil;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetailsFragment extends BaseFragment implements MovieDetailsContract {

    public static final String TAG = MovieDetailsFragment.class.getName();

    private CoordinatorLayout coordinatorLayout;
    private Toolbar toolbar;
    private ImageView imgBackdrop;
    private TextView tvTitle;
    private ImageView imgUnderEighteen;
    private ImageView imgPoster;
    private TextView tvVoteAverage;
    private TextView tvVoteCount;
    private TextView tvLanguage;
    private TextView tvGenres;
    private TextView tvHomepage;
    private TextView tvOverview;

    private ProgressDialog mProgress;
    private Bundle args;
    private long movieId;
    private MovieDetailsContract.Presenter movieDetailsPresenter;

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

        if(getActivity() != null && getActivity() instanceof MainActivity){
            ((MainActivity)getActivity()).hideToolbar();
        }

        if(args != null){
            movieId = args.getLong(Movie.BUNDLE_ID);

            movieDetailsPresenter.getMovieById(movieId);
        }

        return rootView;
    }

    @Override
    void setup(View rootView) {
        args = getArguments();

        coordinatorLayout = rootView.findViewById(R.id.coordinator_movie);
        toolbar = rootView.findViewById(R.id.toolbar);
        imgBackdrop = rootView.findViewById(R.id.img_backdrop);
        tvTitle = rootView.findViewById(R.id.tv_title);
        imgUnderEighteen = rootView.findViewById(R.id.img_under_eighteen);
        imgPoster = rootView.findViewById(R.id.img_poster);
        tvVoteAverage = rootView.findViewById(R.id.tv_vote_average);
        tvVoteCount = rootView.findViewById(R.id.tv_vote_count);
        tvLanguage = rootView.findViewById(R.id.tv_language);
        tvGenres = rootView.findViewById(R.id.tv_genres);
        tvHomepage = rootView.findViewById(R.id.tv_homepage);
        tvOverview = rootView.findViewById(R.id.tv_overview);

        toolbar.setNavigationOnClickListener(view -> {
            if(getActivity() != null) {
                getActivity().onBackPressed();
            }
            else{
                FragmentUtil.removeFragment(getFragmentManager());
            }
        });

        movieDetailsPresenter = new MovieDetailsPresenter(getActivity(), this);
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
    public void onSuccess(Movie movie) {
        if(movie != null){
            toolbar.setTitle(movie.getTitle());

            imgBackdrop.setImageDrawable(movie.getBackdropDrawable());
            tvTitle.setText(movie.getTitle());
            if(movie.isAdult()){
                imgUnderEighteen.setVisibility(View.VISIBLE);
            }
            assert getActivity() != null;
            Glide.with(getActivity())
                    .load(AppConstants.BASE_URL_IMAGE + "/w500" + movie.getPosterPath())
                    .into(imgPoster);

            tvVoteAverage.setText(String.valueOf(movie.getVoteAverage()));
            tvVoteCount.setText(String.valueOf(movie.getVoteCount()));
            tvLanguage.setText(LanguageUtil.getEntireLanguageNameFromCode(movie.getOriginalLanguage()));

            StringBuilder genres = new StringBuilder();

            for(Genre genre : movie.getGenres()){
                genres.append(genre.getName()).append(" | ");
            }

            tvGenres.setText(genres.toString().substring(0, genres.toString().length() - 3));

            if(movie.getHomepage() != null) {
                tvHomepage.setVisibility(View.VISIBLE);
                tvHomepage.setOnClickListener(view -> {
                    Intent intent= new Intent(Intent.ACTION_VIEW, Uri.parse(movie.getHomepage()));
                    startActivity(intent);
                });
            }

            tvOverview.setText(movie.getOverview());
        }
    }

    @Override
    public void onError(String msg) {
        CommonUtil.showSnackbar(getActivity(), coordinatorLayout, msg, Snackbar.LENGTH_LONG);
    }
}

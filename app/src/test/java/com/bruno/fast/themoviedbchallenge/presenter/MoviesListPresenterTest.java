package com.bruno.fast.themoviedbchallenge.presenter;

import android.content.Context;

import com.bruno.fast.themoviedbchallenge.data.model.Movie;
import com.bruno.fast.themoviedbchallenge.presentation.MoviesListContract;
import com.bruno.fast.themoviedbchallenge.presentation.MoviesListPresenter;
import com.google.common.collect.Lists;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.mockito.Mockito.verify;

/**
 * Created by brunopardini on 27/11/17.
 */

public class MoviesListPresenterTest {

    private static ArrayList<Movie> MOVIES = Lists.newArrayList(new Movie("Thor: Ragnarok"), new Movie("Justice League"), new Movie("Wonder Woman"));

    @Mock
    private MoviesListContract moviesListView;

    @Mock
    private Context context;

    private MoviesListContract.Presenter moviesListPresenter;

    @Before
    public void setupMoviesPresenter(){
        MockitoAnnotations.initMocks(this);

        moviesListPresenter = new MoviesListPresenter(context, moviesListView);
    }

    @Test
    public void loadMoviesFromRepository(){
        moviesListPresenter.getMoviesList(28); //action movies

        verify(moviesListView).hideProgress();
        verify(moviesListView).onSuccess(MOVIES);
    }

}

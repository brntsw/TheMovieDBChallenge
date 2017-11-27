package com.bruno.fast.themoviedbchallenge.presenter;

import android.content.Context;

import com.bruno.fast.themoviedbchallenge.data.model.Genre;
import com.bruno.fast.themoviedbchallenge.presentation.GenresListContract;
import com.bruno.fast.themoviedbchallenge.presentation.GenresListPresenter;
import com.google.common.collect.Lists;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brunopardini on 27/11/17.
 */

public class GenresListPresenterTest {

    private static ArrayList<Genre> GENRES = Lists.newArrayList(new Genre(1, "Action"), new Genre(2, "Adventure"), new Genre(3, "Sci fi"));

    @Mock
    private GenresListContract genresView;

    @Mock
    private Context context;

    private GenresListPresenter genresListPresenter;

    @Before
    public void setupGenresPresenter(){
        MockitoAnnotations.initMocks(this);

        genresListPresenter = new GenresListPresenter(context, genresView);
    }

    @Test
    public void loadGenresFromRepository(){
        genresListPresenter.getGenresList();

        verify(genresView).hideProgress();
        verify(genresView).onSuccess(GENRES);
    }

}

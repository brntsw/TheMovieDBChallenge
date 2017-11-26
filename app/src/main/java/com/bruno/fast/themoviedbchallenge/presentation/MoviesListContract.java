package com.bruno.fast.themoviedbchallenge.presentation;

import com.bruno.fast.themoviedbchallenge.data.model.Movie;

import java.util.ArrayList;

/**
 * Created by brunopardini on 11/26/17.
 */

public interface MoviesListContract extends GeneralContract<ArrayList<Movie>> {

    interface Presenter{
        void getMoviesList(int genreId);
    }

}

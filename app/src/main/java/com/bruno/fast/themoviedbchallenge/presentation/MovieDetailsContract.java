package com.bruno.fast.themoviedbchallenge.presentation;

import com.bruno.fast.themoviedbchallenge.data.model.Movie;

import java.util.ArrayList;

/**
 * Created by brunopardini on 11/28/17.
 */

public interface MovieDetailsContract extends GeneralContract<Movie> {

    interface Presenter{
        void getMovieById(long id);
    }

}

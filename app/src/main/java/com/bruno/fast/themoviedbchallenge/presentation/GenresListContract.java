package com.bruno.fast.themoviedbchallenge.presentation;

import com.bruno.fast.themoviedbchallenge.data.model.Genre;

import java.util.ArrayList;

/**
 * Created by brunopardini on 11/25/17.
 */

public interface GenresListContract extends GeneralContract<ArrayList<Genre>> {

    interface Presenter{
        void getGenresList();
    }

}

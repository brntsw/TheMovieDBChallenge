package com.bruno.fast.themoviedbchallenge.presentation;

/**
 * Created by brunopardini on 11/25/17.
 */

public interface GeneralContract<T> {

    void showProgress();
    void hideProgress();
    void onSuccess(T t);
    void onError(String msg);

}

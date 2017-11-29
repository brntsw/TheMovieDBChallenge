package com.bruno.fast.themoviedbchallenge.presentation;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.Log;

import com.bruno.fast.themoviedbchallenge.R;
import com.bruno.fast.themoviedbchallenge.data.json.MovieJsonConverter;
import com.bruno.fast.themoviedbchallenge.data.json.MoviesListJsonConverter;
import com.bruno.fast.themoviedbchallenge.data.model.Movie;
import com.bruno.fast.themoviedbchallenge.remote.RestApi;
import com.bruno.fast.themoviedbchallenge.util.AppConstants;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import org.json.JSONException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by brunopardini on 11/28/17.
 */

public class MovieDetailsPresenter implements MovieDetailsContract.Presenter {

    public static final String TAG = MovieDetailsPresenter.class.getName();

    private Context context;
    private MovieDetailsContract movieDetailsView;
    private RestApi restApi;

    public MovieDetailsPresenter(Context context, MovieDetailsContract movieDetailsView){
        this.context = context;
        this.movieDetailsView = movieDetailsView;
        restApi = RestApi.Builder.build(AppConstants.BASE_URL);
    }

    @Override
    public void getMovieById(long id) {
        movieDetailsView.showProgress();

        Call<String> getMovieCall = restApi.getMovieById(id, AppConstants.THE_MOVIE_DB_KEY);
        getMovieCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                if(response.isSuccessful()){
                    if(response.code() == AppConstants.STATUS_CODE_SUCCESS){
                        Log.d(TAG, response.body());
                        MovieJsonConverter converter = new MovieJsonConverter();
                        try {
                            Movie movie = converter.getMovie(response.body());

                            if(movie.getBackdropPath() != null){
                                Glide.with(context)
                                        .load(AppConstants.BASE_URL_IMAGE + "/w500" + movie.getBackdropPath())
                                        .into(new SimpleTarget<Drawable>() {
                                            @Override
                                            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                                                movieDetailsView.hideProgress();
                                                movie.setBackdropDrawable(resource);

                                                movieDetailsView.onSuccess(movie);
                                            }
                                        });
                            }
                            else{
                                movieDetailsView.hideProgress();
                                movieDetailsView.onSuccess(movie);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            movieDetailsView.onError(context.getString(R.string.generic_error));
                        }
                    }
                    else{
                        movieDetailsView.onError(context.getString(R.string.error_load_movie));
                    }
                }
                else{
                    Log.d(TAG, "Error: " + response.errorBody()+"");
                    movieDetailsView.onError(context.getString(R.string.generic_error));
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                movieDetailsView.hideProgress();
                Log.d(TAG, t.getMessage());
                movieDetailsView.onError(context.getString(R.string.generic_error));
            }
        });
    }
}

package com.bruno.fast.themoviedbchallenge.presentation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.bruno.fast.themoviedbchallenge.R;
import com.bruno.fast.themoviedbchallenge.data.json.MoviesListJsonConverter;
import com.bruno.fast.themoviedbchallenge.remote.RestApi;
import com.bruno.fast.themoviedbchallenge.util.AppConstants;

import org.json.JSONException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by brunopardini on 11/26/17.
 */

public class MoviesListPresenter implements MoviesListContract.Presenter {

    public static final String TAG = MoviesListPresenter.class.getName();

    private Context context;
    private MoviesListContract moviesListView;
    private RestApi restApi;

    public MoviesListPresenter(Context context, MoviesListContract moviesListView){
        this.context = context;
        this.moviesListView = moviesListView;
        restApi = RestApi.Builder.build(AppConstants.BASE_URL);
    }

    @Override
    public void getMoviesList(int genreId) {
        moviesListView.showProgress();

        Call<String> moviesListCall = restApi.getMoviesByGenre(genreId, AppConstants.THE_MOVIE_DB_KEY);

        moviesListCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                moviesListView.hideProgress();

                if(response.isSuccessful()){
                    if(response.code() == AppConstants.STATUS_CODE_SUCCESS){
                        Log.d(TAG, response.body());
                        MoviesListJsonConverter converter = new MoviesListJsonConverter();
                        try {
                            moviesListView.onSuccess(converter.getMovies(response.body()));
                        } catch (JSONException e) {
                            e.printStackTrace();
                            moviesListView.onError(context.getString(R.string.generic_error));
                        }
                    }
                    else{
                        moviesListView.onError(context.getString(R.string.error_load_genres));
                    }
                }
                else{
                    Log.d(TAG, "Error: " + response.errorBody()+"");
                    moviesListView.onError(context.getString(R.string.generic_error));
                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                moviesListView.hideProgress();
                Log.d(TAG, t.getMessage());
                moviesListView.onError(context.getString(R.string.generic_error));
            }
        });
    }
}

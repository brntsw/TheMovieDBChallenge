package com.bruno.fast.themoviedbchallenge.presentation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.bruno.fast.themoviedbchallenge.R;
import com.bruno.fast.themoviedbchallenge.data.json.GenreListJsonConverter;
import com.bruno.fast.themoviedbchallenge.remote.RestApi;
import com.bruno.fast.themoviedbchallenge.util.AppConstants;

import org.json.JSONException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by brunopardini on 11/25/17.
 */

public class GenresListPresenter implements GenresListContract.Presenter{

    public static final String TAG = GenresListPresenter.class.getName();

    private Context context;
    private GenresListContract genresListView;
    private RestApi restApi;

    public GenresListPresenter(Context context, GenresListContract genresListView){
        this.context = context;
        this.genresListView = genresListView;
        this.restApi = RestApi.Builder.build(AppConstants.BASE_URL);
    }

    @Override
    public void getGenresList() {
        genresListView.showProgress();

        Call<String> genresListCall = restApi.getGenres(AppConstants.THE_MOVIE_DB_KEY);
        genresListCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                genresListView.hideProgress();

                if(response.isSuccessful()){
                    if(response.code() == AppConstants.STATUS_CODE_SUCCESS){
                        Log.d(TAG, response.body());
                        GenreListJsonConverter converter = new GenreListJsonConverter();
                        try {
                            genresListView.onSuccess(converter.getGenres(response.body()));
                        } catch (JSONException e) {
                            e.printStackTrace();
                            genresListView.onError(context.getString(R.string.generic_error));
                        }
                    }
                    else{
                        genresListView.onError(context.getString(R.string.error_load_genres));
                    }
                }
                else{
                    Log.d(TAG, "Error: " + response.errorBody()+"");
                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                genresListView.hideProgress();
                Log.e(TAG, t.getMessage());
                genresListView.onError(context.getString(R.string.generic_error));
            }
        });
    }
}

package com.bruno.fast.themoviedbchallenge.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bruno.fast.themoviedbchallenge.R;
import com.bruno.fast.themoviedbchallenge.data.model.Movie;
import com.bruno.fast.themoviedbchallenge.util.AppConstants;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by brunopardini on 11/26/17.
 */

public class MoviesAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<Movie> movies;
    private LayoutInflater inflater = null;

    public MoviesAdapter(Activity activity, ArrayList<Movie> movieList){
        this.activity = activity;
        this.movies = movieList;
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        TextView tvMovieTitle;
        ImageView imgMovie;

        Movie movie = movies.get(position);

        if(convertView == null){
            v = inflater.inflate(R.layout.item_movie, parent, false);
            v.setTag(R.id.tv_movie_title, v.findViewById(R.id.tv_movie_title));
            v.setTag(R.id.img_movie, v.findViewById(R.id.img_movie));
            v.setTag(movie);
        }

        tvMovieTitle = (TextView)v.getTag(R.id.tv_movie_title);
        imgMovie = (ImageView)v.getTag(R.id.img_movie);

        tvMovieTitle.setText(movie.getTitle());
        Glide.with(activity)
                .load(AppConstants.BASE_URL_IMAGE + "/w500" + movie.getPosterPath())
                .into(imgMovie);

        return v;
    }
}

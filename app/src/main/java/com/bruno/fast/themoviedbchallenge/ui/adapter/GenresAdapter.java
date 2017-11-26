package com.bruno.fast.themoviedbchallenge.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bruno.fast.themoviedbchallenge.R;
import com.bruno.fast.themoviedbchallenge.data.model.Genre;

import java.util.ArrayList;

/**
 * Created by brunopardini on 11/26/17.
 */

public class GenresAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<Genre> genres;
    private LayoutInflater inflater = null;

    public GenresAdapter(Activity activity, ArrayList<Genre> genres){
        this.activity = activity;
        this.genres = genres;
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return genres.size();
    }

    @Override
    public Object getItem(int i) {
        return genres.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        TextView tvMovieTitle;

        Genre genre = genres.get(position);

        if(convertView == null){
            v = inflater.inflate(R.layout.item_genre, parent, false);
            v.setTag(R.id.tv_movie_title, v.findViewById(R.id.tv_movie_title));
            v.setTag(genre);
        }

        tvMovieTitle = (TextView)v.getTag(R.id.tv_movie_title);

        tvMovieTitle.setText(activity.getString(R.string.genre_name, genre.getName()));

        return v;
    }
}

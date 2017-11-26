package com.bruno.fast.themoviedbchallenge.data.json;

import com.bruno.fast.themoviedbchallenge.data.model.Genre;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by brunopardini on 11/25/17.
 */

public class GenreListJsonConverter {

    public static final String INDEX_GENRES = "genres";
    public static final String INDEX_ID = "id";
    public static final String INDEX_NAME = "name";

    public ArrayList<Genre> getGenres(String json) throws JSONException {
        ArrayList<Genre> genres = new ArrayList<>();

        JSONObject jsonObjGenres = new JSONObject(json);

        JSONArray jsonArrayGenres = jsonObjGenres.getJSONArray(INDEX_GENRES);

        for(int i = 0; i < jsonArrayGenres.length(); i++){
            Genre genre = new Genre();

            JSONObject jsonObjGenre = jsonArrayGenres.getJSONObject(i);

            int id = jsonObjGenre.getInt(INDEX_ID);
            String name = jsonObjGenre.getString(INDEX_NAME);

            genre.setId(id);
            genre.setName(name);

            genres.add(genre);
        }

        return genres;
    }

}

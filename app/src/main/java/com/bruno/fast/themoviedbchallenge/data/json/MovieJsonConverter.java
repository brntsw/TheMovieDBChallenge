package com.bruno.fast.themoviedbchallenge.data.json;

import com.bruno.fast.themoviedbchallenge.data.model.Genre;
import com.bruno.fast.themoviedbchallenge.data.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brunopardini on 11/28/17.
 */

public class MovieJsonConverter {

    public Movie getMovie(String json) throws JSONException {
        JSONObject jsonObjectMovie = new JSONObject(json);

        Movie movie = new Movie();

        boolean isAdult = jsonObjectMovie.getBoolean("adult");
        String backdropPath = jsonObjectMovie.getString("backdrop_path");
        JSONArray jsonGenres = jsonObjectMovie.getJSONArray("genres");

        ArrayList<Genre> genres = new ArrayList<>();

        if(jsonGenres != null) {
            for (int j = 0; j < jsonGenres.length(); j++){
                Genre genre = new Genre();

                JSONObject jsonGenre = jsonGenres.getJSONObject(j);
                int id = jsonGenre.getInt("id");
                String name = jsonGenre.getString("name");

                genre.setId(id);
                genre.setName(name);

                genres.add(genre);
            }
        }

        long id = jsonObjectMovie.getLong("id");
        String originalLanguage = jsonObjectMovie.getString("original_language");
        String originalTitle = jsonObjectMovie.getString("original_title");
        String overview = jsonObjectMovie.getString("overview");
        String releaseDate = jsonObjectMovie.getString("release_date");
        String posterPath = jsonObjectMovie.getString("poster_path");
        String title = jsonObjectMovie.getString("title");
        int voteCount = jsonObjectMovie.getInt("vote_count");
        double voteAverage = jsonObjectMovie.getDouble("vote_average");

        movie.setAdult(isAdult);
        movie.setBackdropPath(backdropPath);
        movie.setGenres(genres);
        movie.setId(id);
        movie.setOriginalLanguage(originalLanguage);
        movie.setOriginalTitle(originalTitle);
        movie.setOverview(overview);
        movie.setPosterPath(posterPath);
        movie.setReleaseDate(releaseDate);
        movie.setTitle(title);
        movie.setVoteAverage(voteAverage);
        movie.setVoteCount(voteCount);

        return movie;
    }

}

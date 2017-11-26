package com.bruno.fast.themoviedbchallenge.data.json;

import com.bruno.fast.themoviedbchallenge.data.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brunopardini on 11/26/17.
 */

public class MoviesListJsonConverter {

    public ArrayList<Movie> getMovies(String json) throws JSONException {
        ArrayList<Movie> movies = new ArrayList<>();

        JSONObject jsonObjectMovies = new JSONObject(json);

        JSONArray jsonArrayMovies = jsonObjectMovies.getJSONArray("results");

        for(int i = 0; i < jsonArrayMovies.length(); i++){
            JSONObject jsonObjectMovie = jsonArrayMovies.getJSONObject(i);

            Movie movie = new Movie();

            boolean isAdult = jsonObjectMovie.getBoolean("adult");
            String backdropPath = jsonObjectMovie.getString("backdrop_path");
            JSONArray jsonGenreIds = jsonObjectMovie.getJSONArray("genre_ids");

            List<Integer> genreIds = new ArrayList<>();

            if(jsonGenreIds != null) {
                for (int j = 0; j < jsonGenreIds.length(); j++){
                    genreIds.add(jsonGenreIds.optInt(j));
                }
            }

            long id = jsonObjectMovie.getLong("id");
            String originalLanguage = jsonObjectMovie.getString("original_language");
            String originalTitle = jsonObjectMovie.getString("original_title");
            String overview = jsonObjectMovie.getString("overview");
            String releaseDate = jsonObjectMovie.getString("release_date");
            String posterPath = jsonObjectMovie.getString("poster_path");
            String title = jsonObjectMovie.getString("title");
            double voteAverage = jsonObjectMovie.getDouble("vote_average");

            movie.setAdult(isAdult);
            movie.setBackdropPath(backdropPath);
            movie.setGenreIds(genreIds);
            movie.setId(id);
            movie.setOriginalLanguage(originalLanguage);
            movie.setOriginalTitle(originalTitle);
            movie.setOverview(overview);
            movie.setPosterPath(posterPath);
            movie.setReleaseDate(releaseDate);
            movie.setTitle(title);
            movie.setVoteAverage(voteAverage);

            movies.add(movie);
        }

        return movies;
    }

}

package com.bruno.fast.themoviedbchallenge.json;

import com.bruno.fast.themoviedbchallenge.data.json.MoviesListJsonConverter;
import com.bruno.fast.themoviedbchallenge.data.model.Movie;

import org.json.JSONException;
import org.junit.Test;

import java.util.ArrayList;
import static org.junit.Assert.assertEquals;

/**
 * Created by brunopardini on 27/11/17.
 */

public class MoviesListParseJsonFromStringTest {

    @Test
    public void convertJsonToMoviesFromString() throws JSONException {
        String json = "{\n" +
                "    \"id\": 28,\n" +
                "    \"page\": 1,\n" +
                "    \"results\": [\n" +
                "        {\n" +
                "            \"adult\": false,\n" +
                "            \"backdrop_path\": \"/5wNUJs23rT5rTBacNyf5h83AynM.jpg\",\n" +
                "            \"genre_ids\": [\n" +
                "                28,\n" +
                "                12,\n" +
                "                35,\n" +
                "                14,\n" +
                "                878\n" +
                "            ],\n" +
                "            \"id\": 284053,\n" +
                "            \"original_language\": \"en\",\n" +
                "            \"original_title\": \"Thor: Ragnarok\",\n" +
                "            \"overview\": \"Thor is imprisoned on the other side of the universe and finds himself in a race against time to get back to Asgard to stop Ragnarok, the destruction of his homeworld and the end of Asgardian civilization, at the hands of an all-powerful new threat, the ruthless Hela.\",\n" +
                "            \"release_date\": \"2017-10-25\",\n" +
                "            \"poster_path\": \"/oSLd5GYGsiGgzDPKTwQh7wamO8t.jpg\",\n" +
                "            \"popularity\": 1059.858517,\n" +
                "            \"title\": \"Thor: Ragnarok\",\n" +
                "            \"video\": false,\n" +
                "            \"vote_average\": 7.6,\n" +
                "            \"vote_count\": 1973\n" +
                "        },\n" +
                "        {\n" +
                "            \"adult\": false,\n" +
                "            \"backdrop_path\": \"/o5T8rZxoWSBMYwjsUFUqTt6uMQB.jpg\",\n" +
                "            \"genre_ids\": [\n" +
                "                28,\n" +
                "                12,\n" +
                "                14,\n" +
                "                878\n" +
                "            ],\n" +
                "            \"id\": 141052,\n" +
                "            \"original_language\": \"en\",\n" +
                "            \"original_title\": \"Justice League\",\n" +
                "            \"overview\": \"Fueled by his restored faith in humanity and inspired by Superman's selfless act, Bruce Wayne and Diana Prince assemble a team of metahumans consisting of Barry Allen, Arthur Curry, and Victor Stone to face the catastrophic threat of Steppenwolf and the Parademons who are on the hunt for three Mother Boxes on Earth.\",\n" +
                "            \"release_date\": \"2017-11-15\",\n" +
                "            \"poster_path\": \"/9rtrRGeRnL0JKtu9IMBWsmlmmZz.jpg\",\n" +
                "            \"popularity\": 951.3333239999999,\n" +
                "            \"title\": \"Justice League\",\n" +
                "            \"video\": false,\n" +
                "            \"vote_average\": 6.9,\n" +
                "            \"vote_count\": 1025\n" +
                "        }\n" +
                "],\n" +
                "    \"total_pages\": 1031,\n" +
                "    \"total_results\": 20618\n" +
                "}";

        MoviesListJsonConverter converter = new MoviesListJsonConverter();
        ArrayList<Movie> movies = converter.getMovies(json);

        //Tests the first movie from the list
        String expectedTitle = "Thor: Ragnarok";
        String posterPath = "/oSLd5GYGsiGgzDPKTwQh7wamO8t.jpg";
        double expectedVoteAverage = 7.6;

        Movie firstMovie = movies.get(0);

        assertEquals(expectedTitle, firstMovie.getTitle());
        assertEquals(posterPath, firstMovie.getPosterPath());
        assertEquals(expectedVoteAverage, firstMovie.getVoteAverage(), 0.1);

        //Tests the first movie from the list
        expectedTitle = "Justice League";
        posterPath = "/9rtrRGeRnL0JKtu9IMBWsmlmmZz.jpg";
        expectedVoteAverage = 6.9;

        Movie secondMovie = movies.get(1);

        assertEquals(expectedTitle, secondMovie.getTitle());
        assertEquals(posterPath, secondMovie.getPosterPath());
        assertEquals(expectedVoteAverage, secondMovie.getVoteAverage(), 0.1);
    }

}

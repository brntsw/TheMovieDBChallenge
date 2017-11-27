package com.bruno.fast.themoviedbchallenge.json;

import com.bruno.fast.themoviedbchallenge.data.json.GenreListJsonConverter;
import com.bruno.fast.themoviedbchallenge.data.model.Genre;
import com.google.common.collect.Lists;

import org.json.JSONException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;

/**
 * Created by brunopardini on 27/11/17.
 */

public class GenresListParseJsonFromStringTest {

    @Test
    public void convertJsonToGenresFromString() {
        String jsonTest = "{\n" +
                "    \"genres\": [\n" +
                "        {\n" +
                "            \"id\": 28,\n" +
                "            \"name\": \"Action\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 12,\n" +
                "            \"name\": \"Adventure\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 16,\n" +
                "            \"name\": \"Animation\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 35,\n" +
                "            \"name\": \"Comedy\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 80,\n" +
                "            \"name\": \"Crime\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 99,\n" +
                "            \"name\": \"Documentary\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 18,\n" +
                "            \"name\": \"Drama\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 10751,\n" +
                "            \"name\": \"Family\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        GenreListJsonConverter jsonConverter = new GenreListJsonConverter();
        ArrayList<Genre> genres = null;
        try {
            genres = jsonConverter.getGenres(jsonTest);

            //Testing the data of the first genre
            String expectedName = "Action";

            Genre firstGenre = genres.get(0);

            assertEquals(expectedName, firstGenre.getName());

            //Testing the data of the second genre
            expectedName = "Adventure";

            Genre secondGenre = genres.get(1);

            assertEquals(expectedName, secondGenre.getName());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}

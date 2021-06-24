package com.example.flixster.models;

import android.util.Log;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class Movie {

    String backdropPath;
    String posterPath;
    String title;
    String overview;
    int id;
    double rating;
    AsyncHttpClient client;
    String MOVIE_VIDEO_URL;
    String trailerId;

    public Movie(JSONObject jsonObject) throws JSONException {
        backdropPath = jsonObject.getString("backdrop_path");
        posterPath = jsonObject.getString("poster_path");
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
        rating = jsonObject.getDouble("vote_average");
        id = jsonObject.getInt("id");

        MOVIE_VIDEO_URL = String.format("https://api.themoviedb.org/3/movie/%s/videos?api_key=b87187fd7abfa4769160250c19b1a9ac", id);
        client = new AsyncHttpClient();
        client.get(MOVIE_VIDEO_URL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Headers headers, JSON json) {
                JSONObject jsonObject1 = json.jsonObject;
                try {
                    JSONObject results = jsonObject1.getJSONArray("results").getJSONObject(0);
                    trailerId = results.getString("key");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int i, Headers headers, String s, Throwable throwable) {

            }
        });
    }






    public static List<Movie> fromJsonArray (JSONArray movieJsonArray) throws JSONException {
        List<Movie> movies = new ArrayList<>();

        for (int i = 0; i < movieJsonArray.length(); i++) {
            movies.add(new Movie(movieJsonArray.getJSONObject(i)));
        }
        return movies;
    }

    public String getBackdropPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", backdropPath);
    }

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public double getRating() {
        return rating;
    }

    public int getId() {
        return id;
    }

    public String getTrailerId() {
        return trailerId;
    }
}

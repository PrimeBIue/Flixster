package com.example.flixster;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    TextView tvTitleDet;
    TextView tvOverviewDet;
    ImageView ivPosterDet;
    RatingBar rbMovie;
    String imageUrl;
    float rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        tvTitleDet = findViewById(R.id.tvTitleDet);
        tvOverviewDet = findViewById(R.id.tvOverviewDet);
        ivPosterDet = findViewById(R.id.ivPosterDet);
        rbMovie = findViewById(R.id.rbMovie);

        getSupportActionBar().setTitle("Movie Details");

        tvTitleDet.setText(getIntent().getStringExtra(MainActivity.KEY_MOVIE_TITLE));
        tvOverviewDet.setText(getIntent().getStringExtra(MainActivity.KEY_MOVIE_OVERVIEW));
        rating = (float) getIntent().getDoubleExtra(MainActivity.KEY_RATING, 0.00)/2;
        imageUrl = getIntent().getStringExtra(MainActivity.KEY_POSTER_PATH);

        GlideApp
                .with(this)
                .load(imageUrl)
                .placeholder(R.drawable.flicks_movie_placeholder)
                .into(ivPosterDet);

        rbMovie.setRating(rating);
    }
}
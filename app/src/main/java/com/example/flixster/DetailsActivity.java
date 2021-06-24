package com.example.flixster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.flixster.databinding.ActivityDetailsBinding;
import com.example.flixster.databinding.ActivityMainBinding;

public class DetailsActivity extends AppCompatActivity {
    public static final int MOVIE_TRAILER_CODE = 21;

    ActivityDetailsBinding detailsActivityBinding;
    
    String imageUrl;
    float rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        detailsActivityBinding = ActivityDetailsBinding.inflate(getLayoutInflater());
        View view = detailsActivityBinding.getRoot();
        setContentView(view);

        // setContentView(R.layout.activity_details);

        getSupportActionBar().setTitle("Movie Details");

        detailsActivityBinding.tvTitleDet.setText(getIntent().getStringExtra(MainActivity.KEY_MOVIE_TITLE));
        detailsActivityBinding.tvOverviewDet.setText(getIntent().getStringExtra(MainActivity.KEY_MOVIE_OVERVIEW));
        rating = (float) getIntent().getDoubleExtra(MainActivity.KEY_RATING, 0.00)/2;
        imageUrl = getIntent().getStringExtra(MainActivity.KEY_POSTER_PATH);

        GlideApp
                .with(this)
                .load(imageUrl)
                .placeholder(R.drawable.flicks_movie_placeholder)
                .into(detailsActivityBinding.ivPosterDet);

        detailsActivityBinding.rbMovie.setRating(rating);
    }


    public void OnClickPoster(View view){
        Intent i = new Intent(DetailsActivity.this, MovieTrailerActivity.class);

        startActivityForResult(i, MOVIE_TRAILER_CODE);
    }
}
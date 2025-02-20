package com.brenoedl.filmejava;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class Video extends AppCompatActivity {
    private VideoView vvTrailer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        getSupportActionBar().hide();

        vvTrailer = findViewById(R.id.vvTrailer);
        String trailer = getIntent().getStringExtra("trailer");

        vvTrailer.setMediaController(new MediaController(this));
        vvTrailer.setVideoURI(Uri.parse(trailer));
        vvTrailer.start();

    }
}
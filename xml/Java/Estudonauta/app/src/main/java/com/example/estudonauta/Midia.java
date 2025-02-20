package com.example.estudonauta;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;

public class Midia extends AppCompatActivity {
    VideoView video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_midia);
        video = findViewById(R.id.video);
        String cam = "android.resource://com.example.estudonauta/" + R.raw.estudonauta;
        Uri arq = Uri.parse(cam);
        video.setVideoURI(arq);
        video.start();
    }

    public void clickVoltar(View view) {
        finish();
    }
}
package com.example.com.proxal;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class alarm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.alarm1);
        mediaPlayer.start();
    }
}

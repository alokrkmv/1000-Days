package com.example.alok.kilkari;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TrackBaby extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_baby);
        getSupportActionBar().setTitle("Baby Growth chart");
    }
}

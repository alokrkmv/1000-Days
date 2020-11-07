package com.example.alok.kilkari;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Exercise extends AppCompatActivity {
    ImageView first;
    ImageView second;
    ImageView third;
    ImageView fourth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        first=(ImageView)findViewById(R.id.womb);
        second=(ImageView)findViewById(R.id.labour);
        third=(ImageView)findViewById(R.id.under);
        fourth=(ImageView)findViewById(R.id.breast);
        getSupportActionBar().setTitle("Exercises");
        first.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=U5CwY4Lo4dg")));
            }
        });
        second.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.youtube.com/watch?v=Rjhi58tbrlI&list=PLIvjnj7qUWHNX_0Iu3q-7_wO48ZXqu8O-")));
            }
        });
        third.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.youtube.com/watch?v=-LT2eD8YPXM")));
            }
        });
        fourth.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.youtube.com/watch?v=Gj1Jw6m8o40&t=2s")));
            }
        });
    }
}

package com.example.alok.kilkari;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Pregnency extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregnency);
        getSupportActionBar().setTitle("Pregnancy");
    }
    public void article(View view){
        Intent intent=new Intent(Pregnency.this,PregnencyTips.class);
        startActivity(intent);
    }
    public void diet(View view){
        Intent intent=new Intent(Pregnency.this,PregnencyDiet.class);
        startActivity(intent);
    }
}


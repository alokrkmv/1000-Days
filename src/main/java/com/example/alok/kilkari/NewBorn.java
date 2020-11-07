package com.example.alok.kilkari;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class NewBorn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_born);
        getSupportActionBar().setTitle("New Born");
    }
    public void tips(View view){
        Intent intent=new Intent(NewBorn.this,BabyTips.class);
        startActivity(intent);
    }
    public void dietplan(View view){
        Intent intent = new Intent(NewBorn.this,BabyDiet.class);
        startActivity(intent);
    }
}

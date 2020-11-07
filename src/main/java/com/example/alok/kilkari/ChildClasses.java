package com.example.alok.kilkari;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ChildClasses extends AppCompatActivity {
    ImageView womb;
    ImageView labour;
    ImageView under;
    ImageView breast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_classes);
        getSupportActionBar().setTitle("Child Birth Classes");
        womb=(ImageView)findViewById(R.id.womb);
        labour=(ImageView)findViewById(R.id.labour);
        under=(ImageView)findViewById(R.id.under);
        breast=(ImageView)findViewById(R.id.breast);
        womb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.youtube.com/watch?v=WH9ZJu4wRUE&list=PLe2OuTlussNvKe8QbIVDJqcCEzsurP9RM")));
            }
        });
        labour.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.youtube.com/watch?v=Uvk08EfgECk&list=PLe2OuTlussNvKe8QbIVDJqcCEzsurP9RM&index=2")));
            }
        });
        under.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.youtube.com/watch?v=1TJU32v0zA8&index=3&list=PLe2OuTlussNvKe8QbIVDJqcCEzsurP9RM")));
            }
        });
        breast.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.youtube.com/watch?v=DQj-Mn0c370&index=9&list=PLe2OuTlussNvKe8QbIVDJqcCEzsurP9RM")));
            }
        });
    }


}

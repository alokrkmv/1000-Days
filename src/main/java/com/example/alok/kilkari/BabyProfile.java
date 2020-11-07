package com.example.alok.kilkari;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class BabyProfile extends AppCompatActivity {
    EditText name_view;
    EditText BirthDay_view;
    EditText Weight_view;
    EditText Height_view;
    EditText Head_view;
    ImageButton Profile_view;
    private String name;
    private String birthday;
    private String weight;
    private String height;
    private String head;
    private static final int GALLERY_REQUESTI=2;
    private Uri uri=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baby_profile);
    }
    public void setProfile(View view){
        name_view=(EditText)findViewById(R.id.textView10);
        BirthDay_view=(EditText)findViewById(R.id.textView11);
        Weight_view=(EditText)findViewById(R.id.textView12);
        Height_view=(EditText)findViewById(R.id.textView13);
        Head_view=(EditText)findViewById(R.id.textView14);
        name=name_view.getText().toString();
        birthday=BirthDay_view.getText().toString();
        weight=Weight_view.getText().toString();
        height=Height_view.getText().toString();
        head=Head_view.getText().toString();

        name_view.setText(name);
        BirthDay_view.setText(birthday);
        Weight_view.setText(weight);
        Height_view.setText(height);
        Head_view.setText(head);
        Toast.makeText(BabyProfile.this,"Profile Updated Successfully",Toast.LENGTH_LONG).show();

    }
    public void imageButtonClicked(View view){
        Intent galleryintent=new Intent(Intent.ACTION_GET_CONTENT);
        galleryintent.setType("image/*");
        startActivityForResult(galleryintent,GALLERY_REQUESTI);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==GALLERY_REQUESTI && resultCode==RESULT_OK){
            uri = data.getData();
            Profile_view = (ImageButton)findViewById(R.id.imageView3);
            Profile_view.setImageURI(uri);
        }
    }
}

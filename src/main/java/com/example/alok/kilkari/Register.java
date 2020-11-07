package com.example.alok.kilkari;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    private EditText nameField;
    private EditText phonenoField;
    private EditText emailField;
    private EditText passwordField;
    private EditText duedateField;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nameField=(EditText)findViewById(R.id.name);
        phonenoField=(EditText)findViewById(R.id.phoneno);
        emailField=(EditText)findViewById(R.id.email);
        passwordField=(EditText)findViewById(R.id.password);
        duedateField=(EditText)findViewById(R.id.dueDate);
        String name= nameField.getText().toString().trim();
        String email= emailField.getText().toString().trim();
        mAuth=FirebaseAuth.getInstance();
        mDatabase= FirebaseDatabase.getInstance().getReference().child("Users");

    }
    public void onStart(){
        super.onStart();

        EditText txtDate=(EditText)findViewById(R.id.dueDate);
        txtDate.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            public void onFocusChange(View view, boolean hasfocus){
                if(hasfocus){
                    DateDialog dialog=new DateDialog(view);
                    FragmentTransaction ft =getFragmentManager().beginTransaction();
                    dialog.show(ft, "DatePicker");

                }
            }

        });
    }
    public void registerButtonClicked(View view){
        final String name= nameField.getText().toString().trim();
        final String phoneno= phonenoField.getText().toString().trim();
        final String email= emailField.getText().toString().trim();
        final String password= passwordField.getText().toString().trim();
        final String duedate= duedateField.getText().toString().trim();
        Users user=new Users();
        user.setEmail(email);
        user.setName(name);
        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(phoneno) && !TextUtils.isEmpty(password)){
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                  if(task.isSuccessful()){
                      String user_id=mAuth.getCurrentUser().getUid();
                      DatabaseReference current_user_db=mDatabase.child(user_id);
                      current_user_db.child("Name").setValue(name);
                      current_user_db.child("Image").setValue("default");
                      current_user_db.child("Email").setValue(email);
                      Toast.makeText(Register.this,"Registration Successfull",Toast.LENGTH_LONG).show();
                      Intent mainIntent=new Intent(Register.this,Login.class);
                      mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                      startActivity(mainIntent);
                  }
                  else
                  {
                      Toast.makeText(Register.this,"You are already Registerd",Toast.LENGTH_LONG).show();
                  }
                }
            });
        }
        else{
            Toast.makeText(Register.this,"Complusory Fields can't be empty",Toast.LENGTH_LONG).show();

        }


    }
    public void saveInfo(View view){
        SharedPreferences sharedPreferences=getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username",nameField.getText().toString());
        editor.putString("email",emailField.getText().toString());
        editor.apply();

    }


}

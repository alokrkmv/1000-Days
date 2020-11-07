package com.example.alok.kilkari;

import android.app.ProgressDialog;
import android.content.Intent;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    private EditText loginEmail;
    private EditText loginPass;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginEmail=(EditText)findViewById(R.id.reg_email);
        loginPass=(EditText)findViewById(R.id.reg_password);

        mAuth=FirebaseAuth.getInstance();
        mDatabase= FirebaseDatabase.getInstance().getReference().child("Users");
    }


    public void loginButtonClicked(View view){
        email=loginEmail.getText().toString().trim();
        String pass=loginPass.getText().toString().trim();

        if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass)){
            mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        checkUserExists();
                    }
                    else{
                        Toast.makeText(Login.this,"Invalid Login Details",Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }
        else{
            Toast.makeText(Login.this,"All Fields are required",Toast.LENGTH_SHORT).show();
        }

    }

    public void signUpButtonClicked(View view){
        Intent intent = new Intent(Login.this,Register.class);
        startActivity(intent);

    }
    public void checkUserExists(){
//        ProgressDialog dialog = ProgressDialog.show(Login.this, "",
//                "Submission in progress. Please wait...", true);
        final String user_id=mAuth.getCurrentUser().getUid();
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild(user_id)){
                    Intent loginInetnt=new Intent(Login.this,Home.class);
                    loginInetnt.putExtra("Email",email);
                    startActivity(loginInetnt);
                    finish();

                    Toast.makeText(Login.this,"Successfully logged In",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Login.this,"Invalid Login Details",Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}

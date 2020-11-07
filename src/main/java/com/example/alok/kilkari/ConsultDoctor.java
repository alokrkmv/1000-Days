package com.example.alok.kilkari;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ConsultDoctor extends AppCompatActivity {
    EditText name_field;
    EditText phone_field;
    EditText age_field;
    EditText message_field;

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult_doctor);
        name_field=(EditText)findViewById(R.id.name);
        phone_field=(EditText)findViewById(R.id.phone);
        age_field=(EditText)findViewById(R.id.age);
        message_field=(EditText)findViewById(R.id.problem);
        getSupportActionBar().setTitle("Consult Doctor");
    }
    public void submitted(View view){
        final String name;
        final String phone;
        final String age;
        final String message;
        name=name_field.getText().toString();
        phone=phone_field.getText().toString();
        age=age_field.getText().toString();
        message=message_field.getText().toString();
        dialog = ProgressDialog.show(this, "Down Loading", "Please wait ...", true);
        if(!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(phone)&&!TextUtils.isEmpty(age)&&!TextUtils.isEmpty(message)){

            Toast.makeText(ConsultDoctor.this,"Your Response is submitted!! We will contact you soon",Toast.LENGTH_LONG).show();

        }
        else{
            Toast.makeText(ConsultDoctor.this,"Please fill complete details",Toast.LENGTH_LONG).show();

        }
        dialog.dismiss();

    }
}

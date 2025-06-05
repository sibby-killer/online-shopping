package com.example.onlineshopping;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Login extends AppCompatActivity {
     EditText fisrtsname, lastname, email, phonenumber, country, address;
     Button cancel, submit;
    String strfirstname, strlastname, stremail, strphonenumber,strcountry,straddress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       fisrtsname = findViewById(R.id.firstname);
       lastname =findViewById(R.id.lastname);
       email =findViewById(R.id.email);
       phonenumber=findViewById(R.id.phonenumber);
       country=findViewById(R.id.Country);
       address= findViewById(R.id.address);
       cancel= findViewById(R.id.cancel);
       submit= findViewById(R.id.submit);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strfirstname = fisrtsname.getText().toString();
                fisrtsname.setText("");
                strlastname = lastname.getText().toString();
                lastname.setText("");
                stremail = email.getText().toString();
                email.setText("");
                strphonenumber = phonenumber.getText().toString();
                phonenumber.setText("");
                strcountry = country.getText().toString();
                country.setText("");
                straddress = address.getText().toString();
                address.setText("");
            }
       });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),Shopping.class);
                startActivity(intent);
            }
        }
        );
    ;}
    }
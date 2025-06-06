package com.example.onlineshopping;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
// ...existing code...
import com.google.firebase.auth.FirebaseAuth; // Add this import

public class Register extends AppCompatActivity {
    EditText fisrtsname, lastname, email, phonenumber, country, address, password; // Add password
    Button cancel, submit;
    String strfirstname, strlastname, stremail, strphonenumber, strcountry, straddress;
    FirebaseAuth mAuth; // Add this line

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        fisrtsname = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);
        email = findViewById(R.id.email);
        phonenumber = findViewById(R.id.phonenumber);
        country = findViewById(R.id.Country);
        address = findViewById(R.id.address);
        password = findViewById(R.id.password); // Add this line
        cancel = findViewById(R.id.cancel);
        submit = findViewById(R.id.submit);
        mAuth = FirebaseAuth.getInstance(); // Add this line

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
                password.setText(""); // Clear password field too
            }
        });

        // ---- REPLACE your submit.setOnClickListener with the following ----
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stremail = email.getText().toString().trim();
                String passwordStr = password.getText().toString().trim();

                if (stremail.isEmpty() || passwordStr.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter email and password", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(stremail, passwordStr)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Registration Successful. Please log in.", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(), "Registration Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}
// ...existing code...
package com.example.onlineshopping;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

// ...hapa ndio kuna click listener to open the log in and registration activity...
public class LoginActivity extends AppCompatActivity {
    EditText email, password;
    Button loginBtn, signUpBtn; // Add signUpBtn here
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.loginBtn);
        signUpBtn = findViewById(R.id.signUpBtn);
        mAuth = FirebaseAuth.getInstance();

        loginBtn.setOnClickListener(v -> {
            String emailStr = email.getText().toString().trim();
            String passwordStr = password.getText().toString().trim();

            if(emailStr.isEmpty() || passwordStr.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Please enter email and password", Toast.LENGTH_SHORT).show();
                return;
            }

            mAuth.signInWithEmailAndPassword(emailStr, passwordStr)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show(); // Fix message
                            startActivity(new Intent(getApplicationContext(), Shopping.class));
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Login Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show(); // Fix message
                        }
                    });
        });

        signUpBtn.setOnClickListener(v -> { // Add this block
            startActivity(new Intent(LoginActivity.this, Register.class));
        });
    }
}
// ...hapa ndio kuna click listener to open the log in and registration activity...
package com.example.onlineshopping;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    // ...existing code...
    public void Login(View view){
        Toast.makeText(getApplicationContext(), "Profile Clicked", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class); // Change Register to LoginActivity
        startActivity(intent);
    }

    public void signin(View view){
        startActivity(new Intent(this, Register.class));
    }
}
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
    public void Login(View view){
        Toast.makeText(getApplicationContext(), "Profile Clicked", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), Register.class);
        startActivity(intent);
    }
}
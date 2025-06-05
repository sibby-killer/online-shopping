package com.example.onlineshopping;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Shopping extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_shopping2);

    }
    /*public void addtocart (View view){
        Toast.makeText(getApplicationContext(), "Added To Cart", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(),cart.class);
        startActivity(intent);
    } */
}
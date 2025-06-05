package com.example.onlineshopping;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class Shopping extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping2);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        // Set click listeners for all "Add to Cart" buttons
        setupAddToCartButtons();
    }

    private void setupAddToCartButtons() {
        // Find all buttons and set their click listeners
        Button[] addToCartButtons = new Button[] {
            findViewById(R.id.addtocart),
            // Add other button IDs here
        };

        for (Button button : addToCartButtons) {
            if (button != null) {
                button.setOnClickListener(v -> handleAddToCart(v));
            }
        }
    }

    public void handleAddToCart(View view) {
        if (mAuth.getCurrentUser() == null) {
            // User not signed in, redirect to login
            Toast.makeText(this, "Please sign in to add items to cart", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, Login.class));
            return;
        }

        // Get the product details based on the clicked button
        View productView = (View) view.getParent();
        String productName = ((android.widget.TextView) productView.findViewById(R.id.producttitle)).getText().toString();
        String priceText = ((android.widget.TextView) productView.findViewById(R.id.productprice)).getText().toString();
        double priceUSD = extractPrice(priceText);

        addItemToCart(productName, priceUSD);
    }

    private double extractPrice(String priceText) {
        // Extract price value from "Price: XX.XX USD" format
        try {
            return Double.parseDouble(priceText.split("USD")[0].split(":")[1].trim());
        } catch (Exception e) {
            return 0.0;
        }
    }

    private void addItemToCart(String productName, double priceUSD) {
        String userId = mAuth.getCurrentUser().getUid();
        CartItem cartItem = new CartItem(productName, productName, priceUSD, 1);

        db.collection("users")
            .document(userId)
            .collection("cart")
            .document(productName)
            .set(cartItem)
            .addOnSuccessListener(aVoid -> {
                Toast.makeText(this, "Added to cart", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, cart.class));
            })
            .addOnFailureListener(e -> {
                Toast.makeText(this, "Failed to add to cart", Toast.LENGTH_SHORT).show();
            });
    }
}
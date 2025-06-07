package com.example.onlineshopping;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class Shopping extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private RelativeLayout cartIconContainer;
    private ImageView cartIcon;
    private TextView cartBadge;
    private int cartCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        cartIconContainer = findViewById(R.id.cartIconContainer);
        cartIcon = findViewById(R.id.cartIcon);
        cartBadge = findViewById(R.id.cartBadge);
        cartIconContainer.setVisibility(View.GONE);

        cartIcon.setOnClickListener(v -> {
            startActivity(new Intent(this, cart.class));
        });

        setupAddToCartButtons();
    }

    private void setupAddToCartButtons() {
        int[] buttonIds = {
                R.id.addtocart1,
                R.id.addtocart2,
                R.id.addtocart3,
                R.id.addtocart4,
                R.id.addtocart5,
                R.id.addtocart6,
                R.id.addtocart7,
                R.id.addtocart8,
                R.id.addtocart9
        };

        for (int i = 0; i < buttonIds.length; i++) {
            Button button = findViewById(buttonIds[i]);
            int productIndex = i + 1;
            if (button != null) {
                button.setOnClickListener(v -> handleAddToCart(productIndex));
            }
        }
    }

    private void handleAddToCart(int productIndex) {
        if (mAuth.getCurrentUser() == null) {
            Toast.makeText(this, "Please sign in to add items to cart", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, LoginActivity.class));
            return;
        }

        String titleId = "producttitle" + productIndex;
        String priceId = "productprice" + productIndex;

        int resTitleId = getResources().getIdentifier(titleId, "id", getPackageName());
        int resPriceId = getResources().getIdentifier(priceId, "id", getPackageName());

        String productName = ((TextView) findViewById(resTitleId)).getText().toString();
        String priceText = ((TextView) findViewById(resPriceId)).getText().toString();
        double priceKSH = extractPrice(priceText);

        addItemToCart(productName, priceKSH);
    }

    private double extractPrice(String priceText) {
        // Extract price value from "Price: XXXX KSH"
        try {
            String[] parts = priceText.split(":");
            if (parts.length > 1) {
                return Double.parseDouble(parts[1].replace("KSH", "").replace("ksh", "").replace(" ", "").trim());
            }
        } catch (Exception e) {
            // ignore
        }
        return 0.0;
    }

    private void addItemToCart(String productName, double priceKSH) {
        String userId = mAuth.getCurrentUser().getUid();
        CartItem cartItem = new CartItem(productName, productName, priceKSH, 1);

        db.collection("users")
                .document(userId)
                .collection("cart")
                .document(productName)
                .set(cartItem)
                .addOnSuccessListener(aVoid -> {
                    cartCount++;
                    cartBadge.setText(String.valueOf(cartCount));
                    cartIconContainer.setVisibility(View.VISIBLE);
                    Toast.makeText(this, "Added successfully", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Failed to add to cart", Toast.LENGTH_SHORT).show();
                });
    }

    // Optionally, add a method to update the cart count from Firestore on resume
    @Override
    protected void onResume() {
        super.onResume();
        updateCartCount();
    }

    private void updateCartCount() {
        if (mAuth.getCurrentUser() == null) {
            cartIconContainer.setVisibility(View.GONE);
            return;
        }
        String userId = mAuth.getCurrentUser().getUid();
        db.collection("users")
                .document(userId)
                .collection("cart")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    int count = queryDocumentSnapshots.size();
                    cartCount = count;
                    if (count > 0) {
                        cartBadge.setText(String.valueOf(count));
                        cartIconContainer.setVisibility(View.VISIBLE);
                    } else {
                        cartIconContainer.setVisibility(View.GONE);
                    }
                });
    }
}
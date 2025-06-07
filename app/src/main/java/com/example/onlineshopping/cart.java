package com.example.onlineshopping;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.ArrayList;
import java.util.List;

public class cart extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private Button submitOrder, orderMore;
    private TextView cartTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = findViewById(R.id.cartrecycleview);
        submitOrder = findViewById(R.id.buttonsubmit);
        orderMore = findViewById(R.id.buttonOrderMore);
        cartTitle = findViewById(R.id.carttitle);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadCartItems();

        orderMore.setOnClickListener(v -> {
            startActivity(new Intent(this, Shopping.class));
            finish();
        });
    }

    private void loadCartItems() {
        if (mAuth.getCurrentUser() == null) return;

        String userId = mAuth.getCurrentUser().getUid();
        db.collection("users")
                .document(userId)
                .collection("cart")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    List<CartItem> cartItems = new ArrayList<>();
                    queryDocumentSnapshots.forEach(doc -> {
                        CartItem item = doc.toObject(CartItem.class);
                        cartItems.add(item);
                    });
                    updateCartDisplay(cartItems);
                });
    }

    private void updateCartDisplay(List<CartItem> items) {
        double total = 0;
        for (CartItem item : items) {
            total += item.getPriceKES() * item.getQuantity();
        }
        cartTitle.setText(String.format("Cart Total: KES %.2f", total));
        CartAdapter adapter = new CartAdapter(items);
        recyclerView.setAdapter(adapter);
    }
}
package com.example.onlineshopping;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private List<CartItem> cartItems;

    public CartAdapter(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem item = cartItems.get(position);
        holder.productName.setText(item.getProductName());
        holder.price.setText("KES " + item.getPriceKES());
        holder.quantity.setText("Qty: " + item.getQuantity());

        // Set image based on product name (add more cases as needed)
        switch (item.getProductName()) {
            case "Rhode Lipgloss":
                holder.productImage.setImageResource(R.drawable.rhode);
                break;
            case "Suede Chocolate Stiletto High Heeled Shoes":
                holder.productImage.setImageResource(R.drawable.highheels);
                break;
            case "Stiletto High Heeled Shoes":
                holder.productImage.setImageResource(R.drawable.heels);
                break;
            case "Brown Matte Hair Clips":
                holder.productImage.setImageResource(R.drawable.clip);
                break;
            case "Hair Clips":
                holder.productImage.setImageResource(R.drawable.hairclips);
                break;
            case "Flower Hair Claw":
                holder.productImage.setImageResource(R.drawable.flowerhairclaw);
                break;
            case "Fall Bags":
                holder.productImage.setImageResource(R.drawable.fall_bags);
                break;
            case "Birdin Bag":
                holder.productImage.setImageResource(R.drawable.birdinbag);
                break;
            case "Commuter HandBag":
                holder.productImage.setImageResource(R.drawable.commuterhandbag);
                break;
            default:
                holder.productImage.setImageResource(R.drawable.ic_launcher_foreground);
        }
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    static class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName, price, quantity;

        CartViewHolder(View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.cart_item_image);
            productName = itemView.findViewById(R.id.cart_item_name);
            price = itemView.findViewById(R.id.cart_item_price);
            quantity = itemView.findViewById(R.id.cart_item_quantity);
        }
    }
}
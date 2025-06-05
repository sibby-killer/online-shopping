package com.example.onlineshopping;

public class CartItem {
    private String productId;
    private String productName;
    private double priceUSD;
    private int quantity;

    public CartItem() {
        // Required empty constructor for Firestore
    }

    public CartItem(String productId, String productName, double priceUSD, int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.priceUSD = priceUSD;
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPriceUSD() {
        return priceUSD;
    }

    public void setPriceUSD(double priceUSD) {
        this.priceUSD = priceUSD;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPriceKES() {
        // Assuming 1 USD = 160 KES (you should use a real-time exchange rate in production)
        return priceUSD * 160;
    }
}
package com.thoughtworks.ketsu.domain.user;

/**
 * Created by pzzheng on 5/21/17.
 */
public class OrderItem {
    private String productId;
    private int quantity;
    private double amount;

    private OrderItem() {}

    public String getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getAmount() {
        return amount;
    }

    public OrderItem(String productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}

package com.drmeph.cartProject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by kdorfer on 2016-11-06.
 */
public class Product {

    private int productId;
    private String name;
    private float rating;
    private float price;

    @JsonIgnore
    private int quantity;

    public Product() {
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isInStock() {
        return this.quantity > 0;
    }
}

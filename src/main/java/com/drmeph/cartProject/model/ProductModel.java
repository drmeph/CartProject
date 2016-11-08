package com.drmeph.cartProject.model;

import com.drmeph.cartProject.entity.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created by kdorfer on 2016-11-07.
 */
//TODO Could have extended Product, but it's a matter of preference I guess
public class ProductModel {
    @NotNull @NotEmpty
    private String name;
    @NotNull
    private Float price;
    @NotNull
    private Integer quantity;

    public ProductModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @JsonIgnore
    public Product toProduct() {
        Product product = new Product();
        product.setName(this.name);
        product.setPrice(this.price);
        product.setQuantity(this.quantity);

        return product;
    }
}

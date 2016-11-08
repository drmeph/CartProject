package com.drmeph.cartProject.model.response;

import com.drmeph.cartProject.entity.Product;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashMap;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

/**
 * Created by kdorfer on 2016-11-07.
 */
public class ListProductsAdminResponseModel extends ResponseModel {
    @JsonInclude(NON_NULL)
    private HashMap<String, Integer> products;

    public ListProductsAdminResponseModel() {
    }

    public HashMap<String, Integer> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {

        if (this.products == null) {
            this.products = new HashMap<>();
        }

        for (Product product:
             products) {
            this.products.put(product.getName(), product.getQuantity());
        }
    }
}

package com.drmeph.cartProject.model.response;

import com.drmeph.cartProject.entity.Product;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashMap;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.*;

/**
 * Created by kdorfer on 2016-11-07.
 */
public class ListProductsResponseModel extends ResponseModel {
    @JsonInclude(NON_NULL)
    private HashMap<Integer, String> products;

    public ListProductsResponseModel() {
        super();
    }

    public HashMap<Integer, String> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {

        for (Product product : products) {

            if (this.products == null) {
                this.products = new HashMap<>();
            }

            this.products.put(product.getProductId(), product.getName());
        }
    }
}

package com.drmeph.cartProject.model.response;

import com.drmeph.cartProject.entity.Product;
import com.fasterxml.jackson.annotation.JsonInclude;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

/**
 * Created by kdorfer on 2016-11-07.
 */
public class ShowProductResponseModel extends ResponseModel {
    @JsonInclude(NON_NULL)
    private Product product;

    public ShowProductResponseModel() {
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}

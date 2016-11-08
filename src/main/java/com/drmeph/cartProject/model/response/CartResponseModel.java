package com.drmeph.cartProject.model.response;

import java.util.Map;

/**
 * Created by kdorfer on 2016-11-07.
 */
public class CartResponseModel extends ResponseModel {

    private Map<Integer, Integer> cart;

    public CartResponseModel() {
        super();
    }

    public Map<Integer, Integer> getCart() {
        return cart;
    }

    public void setCart(Map<Integer, Integer> cart) {
        this.cart = cart;
    }
}

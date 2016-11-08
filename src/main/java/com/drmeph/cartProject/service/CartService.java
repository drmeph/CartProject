package com.drmeph.cartProject.service;

import com.drmeph.cartProject.exception.DAOException;

import java.util.Map;

/**
 * Created by kdorfer on 2016-11-07.
 */
public interface CartService {

    void addToCart(int productId) throws DAOException;
    void removeFromCart(int productId) throws DAOException;
    Map<Integer, Integer> getCart();
}

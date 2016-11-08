package com.drmeph.cartProject.service.impl;

import com.drmeph.cartProject.dao.ProductDAO;
import com.drmeph.cartProject.exception.DAOException;
import com.drmeph.cartProject.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;

/**
 * Created by kdorfer on 2016-11-07.
 */
@Service("cartService")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "session")
public class CartServiceImpl implements CartService, Serializable {
    private static final long serialVersionUID = 129498385;

    private HashMap<Integer, Integer> cart;

    @Autowired
    ProductDAO productDAO;

    @Autowired
    MessageSource messageSource;

    /**
     *
     * @param productId
     * @throws DAOException
     */
    @Override
    public void addToCart(int productId) throws DAOException {

        if (productDAO.lockUnlockProduct(productId, false)) {
            addRemoveProductToCart(productId, false);
        }
    }

    /**
     *
     * @param productId
     * @throws DAOException
     */
    @Override
    public void removeFromCart(int productId) throws DAOException {

        if (!getCart().containsKey(productId) || getCart().get(productId) <= 0) {
            throw new DAOException(messageSource.getMessage("noLockedProduct.daoexception.message", null,
                    Locale.ENGLISH));
        }

        if (productDAO.lockUnlockProduct(productId, true)) {
            addRemoveProductToCart(productId, true);
        }
    }

    /**
     *
     * @return
     */
    @Override
    public HashMap<Integer, Integer> getCart() {

        if (cart == null) {
            cart = new HashMap<>();
        }

        return cart;
    }

    /**
     * Method for resetting the cart (testing purpose)
     *
     * @param cart
     */
    void setCart(HashMap<Integer, Integer> cart) {
        this.cart = cart;
    }

    /**
     *
     * @param productId
     */
    void addRemoveProductToCart(int productId, boolean remove) {

        if (getCart().containsKey(productId)) {
            getCart().put(productId,
                    remove ? getCart().get(productId) - 1 : getCart().get(productId) + 1);
        } else {
            getCart().put(productId, 1);
        }
    }
}

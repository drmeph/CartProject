package com.drmeph.cartProject.dao;

import com.drmeph.cartProject.exception.DAOException;
import com.drmeph.cartProject.entity.Product;

import java.util.List;

/**
 * Created by kdorfer on 2016-11-06.
 */
public interface ProductDAO {
    List<Product> getProducts() throws DAOException;
    Product getProduct(int productId) throws DAOException;
    void saveProduct(Product product) throws DAOException;
    void deleteProduct(int productId) throws DAOException;
    boolean lockUnlockProduct(int productId, boolean unlock) throws DAOException;
    void rateProduct(int productId, int rating) throws DAOException;
}

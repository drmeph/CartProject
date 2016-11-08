package com.drmeph.cartProject.service;

import com.drmeph.cartProject.entity.Product;
import com.drmeph.cartProject.exception.DAOException;
import com.drmeph.cartProject.model.ProductModel;
import com.drmeph.cartProject.model.RatingModel;

import java.util.List;

/**
 * Created by kdorfer on 2016-11-07.
 */
public interface ProductService {
    List<Product> getListProducts() throws DAOException;
    Product getProduct(int productId) throws DAOException;
    void addProduct(ProductModel productModel) throws DAOException;
    void deleteProduct(int productId) throws DAOException;
    void rateProduct(RatingModel ratingModel) throws DAOException;
}

package com.drmeph.cartProject.service.impl;

import com.drmeph.cartProject.dao.ProductDAO;
import com.drmeph.cartProject.entity.Product;
import com.drmeph.cartProject.exception.DAOException;
import com.drmeph.cartProject.model.ProductModel;
import com.drmeph.cartProject.model.RatingModel;
import com.drmeph.cartProject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kdorfer on 2016-11-07.
 */
@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDAO productDAO;

    /**
     *
     * @return
     * @throws DAOException
     */
    @Override
    public List<Product> getListProducts() throws DAOException {
        return productDAO.getProducts();
    }

    /**
     *
     * @param productId
     * @return
     * @throws DAOException
     */
    @Override
    public Product getProduct(int productId) throws DAOException {
        return productDAO.getProduct(productId);
    }

    /**
     *
     * @param productModel
     * @throws DAOException
     */
    @Override
    public void addProduct(ProductModel productModel) throws DAOException {
        productDAO.saveProduct(productModel.toProduct());
    }

    /**
     *
     * @param productId
     * @throws DAOException
     */
    @Override
    public void deleteProduct(int productId) throws DAOException {
        productDAO.deleteProduct(productId);
    }

    /**
     *
     * @param ratingModel
     * @throws DAOException
     */
    @Override
    public void rateProduct(RatingModel ratingModel) throws DAOException {
        productDAO.rateProduct(ratingModel.getProductId(), ratingModel.getRating());
    }
}

package com.drmeph.cartProject.dao.impl;

import com.drmeph.cartProject.dao.ProductDAO;
import com.drmeph.cartProject.dao.RatingDAO;
import com.drmeph.cartProject.exception.DAOException;
import com.drmeph.cartProject.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

/**
 * Created by kdorfer on 2016-11-06.
 */
@Repository(value = "productDAO")
public class ProductDAOImpl implements ProductDAO {
    /*
     * SQL REQUESTS
     */
    private static final String GET_LIST_PRODUCT_SQL_REQUEST = "SELECT * FROM cartproject.Product";
    private static final String GET_PRODUCT_SQL_REQUEST = "SELECT * FROM cartproject.Product WHERE productId = ?";
    private static final String INSERT_PRODUCT_SQL_REQUEST = "INSERT INTO cartproject.Product (name, price, quantity) " +
            "VALUES (?, ?, ?)";
    private static final String UPDATE_PRODUCT_SQL_REQUEST = "UPDATE cartproject.Product SET name = ?, price = ?, " +
            "quantity = ?";
    private static final String DELETE_PRODUCT_SQL_REQUEST = "DELETE FROM cartproject.Product WHERE productId = ?";
    private static final String LOCK_PRODUCT_SQL_REQUEST = "UPDATE cartproject.Product SET quantity = quantity - 1" +
            " WHERE productId = ? AND quantity > 0";
    private static final String UNLOCK_PRODUCT_SQL_REQUEST = "UPDATE cartproject.Product SET quantity = quantity + 1" +
            " WHERE productId = ?";
    private static final String RATE_PRODUCT_SQL_REQUEST = "UPDATE cartproject.Product p, " +
            "(select sum(r.rate) as sum, count(r.productId) as count from cartproject.Rating r) i " +
            "SET p.rating = i.sum / i.count WHERE productId = ?;";


    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    MessageSource messageSource;

    @Autowired
    RatingDAO ratingDAO;

    /**
     *
     * @return
     * @throws DAOException
     */
    @Override
    public List<Product> getProducts() throws DAOException {
        List<Product> products;

        try {
            products = jdbcTemplate.query(GET_LIST_PRODUCT_SQL_REQUEST, new ProductRowMapper());
        } catch (DataAccessException e) {
            throw new DAOException(messageSource.getMessage("getProducts.daoexception.message", null, Locale.ENGLISH));
        }

        return products;
    }

    /**
     *
     * @param productId
     * @return
     * @throws DAOException
     */
    @Override
    public Product getProduct(int productId) throws DAOException {
        Object[] params = new Object[] {productId};
        Product product;

        try {
            product = jdbcTemplate.queryForObject(GET_PRODUCT_SQL_REQUEST, params, new ProductRowMapper());
        } catch (DataAccessException e) {
            throw new DAOException(messageSource.getMessage("getProduct.daoexception.message", null, Locale.ENGLISH));
        }

        return product;
    }

    /**
     *
     * @param product
     * @throws DAOException
     */
    @Override
    public void saveProduct(Product product) throws DAOException {
        String sqlRequest;
        Object[] params;

        if (product.getProductId() <= 0) {
            sqlRequest = INSERT_PRODUCT_SQL_REQUEST;
            params = new Object[] {product.getName(), product.getPrice(), product.getQuantity()};
        } else {
            sqlRequest = UPDATE_PRODUCT_SQL_REQUEST;
            params = new Object[] {};
        }

        try {
            jdbcTemplate.update(sqlRequest, params);
        } catch (DataAccessException e) {
            throw new DAOException(messageSource.getMessage("saveProduct.daoexception.message", null, Locale.ENGLISH));
        }
    }

    /**
     *
     * @param productId
     * @throws DAOException
     */
    @Override
    public void deleteProduct(int productId) throws DAOException {
        try {
            jdbcTemplate.update(DELETE_PRODUCT_SQL_REQUEST, productId);
        } catch (DataAccessException e) {
            throw new DAOException(messageSource.getMessage("deleteProduct.daoexception.message", null, Locale.ENGLISH));
        }
    }

    /**
     *
     * @param productId
     * @param unlock
     * @return
     * @throws DAOException
     */
    @Override
    public boolean lockUnlockProduct(int productId, boolean unlock) throws DAOException {
        Object[] params = new Object[] {productId};
        int result;
        String sqlRequest = unlock ? UNLOCK_PRODUCT_SQL_REQUEST : LOCK_PRODUCT_SQL_REQUEST;

        try {
            result = jdbcTemplate.update(sqlRequest, params);
        } catch (DataAccessException e) {
            String errorMessage = unlock ? "unlockProduct.daoexception.message" : "lockProduct.daoexception.message";
            throw new DAOException(messageSource.getMessage(errorMessage, null, Locale.ENGLISH));
        }

        return result > 0;
    }

    /**
     *
     * @param productId
     * @param rating
     * @throws DAOException
     */
    @Override
    public void rateProduct(int productId, int rating) throws DAOException {
        Object[] params = new Object[] {productId};

        try {
            ratingDAO.addRating(productId, rating);
            jdbcTemplate.update(RATE_PRODUCT_SQL_REQUEST, params);
        } catch (DAOException e) {
            throw e;
        } catch (DataAccessException e) {
            throw new DAOException("boom");
        }
    }


    /**
     *
     */
    private class ProductRowMapper implements RowMapper<Product> {

        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            ProductExtractor productExtractor = new ProductExtractor();
            return productExtractor.extractData(rs);
        }
    }

    /**
     *
     */
    private class ProductExtractor implements ResultSetExtractor<Product> {

        @Override
        public Product extractData(ResultSet rs) throws SQLException, DataAccessException {

            Product product = new Product();
            product.setProductId(rs.getInt("productId"));
            product.setName(rs.getString("name"));
            product.setRating(rs.getFloat("rating"));
            product.setPrice(rs.getFloat("price"));
            product.setQuantity(rs.getInt("quantity"));

            return product;
        }
    }
}

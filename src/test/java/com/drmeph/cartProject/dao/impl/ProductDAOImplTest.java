package com.drmeph.cartProject.dao.impl;

import com.drmeph.cartProject.configuration.TestConfiguration;
import com.drmeph.cartProject.exception.DAOException;
import com.drmeph.cartProject.entity.Product;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.Locale;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

/**
 * Created by kdorfer on 2016-11-06.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class, loader = AnnotationConfigContextLoader.class)
public class ProductDAOImplTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    ProductDAOImpl testProductDAO;

    @Autowired
    MessageSource messageSource;

    private Product product;

    @Before
    public void setUp() throws Exception {
        product = new Product();
        when(messageSource.getMessage(anyString(),
                any(Object[].class), any(Locale.class))).thenReturn("test message");
    }

    @After
    public void tearDown() throws Exception {
        product = null;
    }

    /**
     * Test that DAOException is thrown if request to database failed
     *
     * @throws Exception
     */
    @Test(expected = DAOException.class)
    public void getProductsWithDAOException() throws Exception {
        doThrow(new MyDataAccessException("KO")).when(jdbcTemplate).query(anyString(), any(RowMapper.class));

        testProductDAO.getProducts();
    }

    /**
     * Test that DAOException is thrown if request to database failed
     *
     * @throws Exception
     */
    @Test(expected = DAOException.class)
    public void getProductWithDAOException() throws Exception {
        doThrow(new MyDataAccessException("KO")).when(jdbcTemplate).queryForObject(anyString(), any(Object[].class),
                any(RowMapper.class));

        testProductDAO.getProduct(1);
    }

    /**
     * Test that DAOException is thrown if request to database failed
     *
     * @throws Exception
     */
    @Test(expected = DAOException.class)
    public void saveProductInsertWithDAOException() throws Exception {
        doThrow(new MyDataAccessException("KO")).when(jdbcTemplate).update(anyString(),
                new Object[] {anyString(), Matchers.anyFloat(), Matchers.anyFloat()});

        testProductDAO.saveProduct(product);
    }

    /**
     * Test that DAOException is thrown if request to database failed
     *
     * @throws Exception
     */
    @Test(expected = DAOException.class)
    public void deleteProduct() throws Exception {
        doThrow(new MyDataAccessException("KO")).when(jdbcTemplate).update(anyString(), anyInt());

        testProductDAO.deleteProduct(1);
    }

    private class MyDataAccessException extends DataAccessException {

        public MyDataAccessException(String msg) {
            super(msg);
        }
    }
}
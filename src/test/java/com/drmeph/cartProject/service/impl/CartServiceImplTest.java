package com.drmeph.cartProject.service.impl;

import com.drmeph.cartProject.configuration.TestCartServiceConfiguration;
import com.drmeph.cartProject.dao.impl.ProductDAOImpl;
import com.drmeph.cartProject.exception.DAOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

/**
 * Created by kdorfer on 2016-11-07.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestCartServiceConfiguration.class,
loader = AnnotationConfigContextLoader.class)
public class CartServiceImplTest {

    @Autowired
    CartServiceImpl testCartService;

    @Autowired
    ProductDAOImpl productDAO;

    @Before
    public void setUp() throws Exception {
        reset(productDAO);
    }

    @After
    public void tearDown() throws Exception {
        testCartService.setCart(null);
    }

    /**
     * Test that after calling add-to-cart to add a new product to the cart,
     * it contain the new productId
     *
     * @throws Exception
     */
    @Test
    public void addToCartSuccess() throws Exception {
        when(productDAO.lockUnlockProduct(1, false)).thenReturn(true);
        testCartService.addToCart(1);

        assertTrue(testCartService.getCart().containsKey(1));
        assertEquals(testCartService.getCart().get(1).intValue(), 1);
    }

    /**
     * Test that after calling add-to-cart to add a new product to the cart,
     * it doesn't contain the new productId if the resource wasn't locked successfully
     *
     * @throws Exception
     */
    @Test
    public void addToCartFailed() throws Exception {
        when(productDAO.lockUnlockProduct(1, false)).thenReturn(false);
        testCartService.addToCart(1);

        assertFalse(testCartService.getCart().containsKey(1));
    }

    /**
     * Test that after calling remove-from-cart it removes the product from the cart
     *
     * @throws Exception
     */
    @Test
    public void removeFromCartSuccess() throws Exception {
        when(productDAO.lockUnlockProduct(1, false)).thenReturn(true);
        testCartService.addToCart(1);

        assertTrue(testCartService.getCart().containsKey(1));
        assertEquals(testCartService.getCart().get(1).intValue(), 1);

        when(productDAO.lockUnlockProduct(1, true)).thenReturn(true);
        testCartService.removeFromCart(1);

        assertTrue(testCartService.getCart().containsKey(1));
        assertEquals(testCartService.getCart().get(1).intValue(), 0);
    }

    /**
     * Test that after calling remove-from-cart it doesn't removes the product from the cart
     * if the resource wasn't unlocked successfully
     *
     * @throws Exception
     */
    @Test
    public void removeFromCartFailed() throws Exception {
        when(productDAO.lockUnlockProduct(1, false)).thenReturn(true);
        testCartService.addToCart(1);

        assertTrue(testCartService.getCart().containsKey(1));
        assertEquals(testCartService.getCart().get(1).intValue(), 1);

        when(productDAO.lockUnlockProduct(1, true)).thenReturn(false);
        testCartService.removeFromCart(1);

        assertTrue(testCartService.getCart().containsKey(1));
        assertEquals(testCartService.getCart().get(1).intValue(), 1);
    }

    /**
     * Test that after calling remove-from-cart from an empty cart it raises a DAO exception
     *
     * @throws Exception
     */
    @Test(expected = DAOException.class)
    public void removeFromCartFailedCauseEmpty() throws Exception {
        testCartService.removeFromCart(1);
    }

    /**
     * Test that after init of the service the cart exist and is empty
     *
     * @throws Exception
     */
    @Test
    public void getCartNotNullAndEmpty() throws Exception {
        assertNotNull(testCartService.getCart());
        assertEquals(testCartService.getCart().size(), 0);
    }
}
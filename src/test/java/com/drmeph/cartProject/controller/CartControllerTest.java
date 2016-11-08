package com.drmeph.cartProject.controller;

import com.drmeph.cartProject.configuration.TestCartConfiguration;
import com.drmeph.cartProject.exception.DAOException;
import com.drmeph.cartProject.service.impl.CartServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Locale;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by kdorfer on 2016-11-07.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestCartConfiguration.class, loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
public class CartControllerTest {
    private static final String ADD_TO_CART_URL = "/cart/add-to-cart?productId=1";
    private static final String REMOVE_FROM_CART_URL = "/cart/remove-from-cart?productId=1";

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private CartServiceImpl cartService;

    @Autowired
    private MessageSource messageSource;

    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        doNothing().when(cartService).addToCart(anyInt());
        when(messageSource.getMessage(anyString(),
                any(Object[].class), any(Locale.class))).thenReturn("test message");
    }

    @After
    public void tearDown() throws Exception {
        mvc = null;
    }

    @Test
    public void addToCart() throws Exception {
        mvc.perform(post(ADD_TO_CART_URL).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    public void addToCartWithDAOException() throws Exception {
        doThrow(DAOException.class).when(cartService).addToCart(anyInt());
       mvc.perform(post(ADD_TO_CART_URL).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void removeFromCart() throws Exception {
        mvc.perform(post(REMOVE_FROM_CART_URL).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    public void removeFromCartWithDAOException() throws Exception {
        doThrow(DAOException.class).when(cartService).addToCart(anyInt());
        mvc.perform(post(REMOVE_FROM_CART_URL).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();
    }
}
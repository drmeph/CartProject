package com.drmeph.cartProject.controller;

import com.drmeph.cartProject.configuration.TestCatalogControllerConfiguration;
import com.drmeph.cartProject.entity.Product;
import com.drmeph.cartProject.exception.DAOException;
import com.drmeph.cartProject.service.impl.ProductServiceImpl;
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

import java.util.ArrayList;
import java.util.Locale;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by kdorfer on 2016-11-07.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestCatalogControllerConfiguration.class,
        loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
public class CatalogControllerTest {
    private static final String LIST_PRODUCTS = "/catalog/list-products";
    private static final String SHOW_PRODUCT = "/catalog/show-product/1";

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private MessageSource messageSource;

    private MockMvc mvc;


    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        reset(productService);
        when(messageSource.getMessage(anyString(),
                any(Object[].class), any(Locale.class))).thenReturn("test message");
    }

    @After
    public void tearDown() throws Exception {
        mvc = null;
    }

    /**
     * Test that a request to list-products return 200
     *
     * @throws Exception
     */
    @Test
    public void getListProducts() throws Exception {
        mvc.perform(get(LIST_PRODUCTS).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    /**
     * Test that a request to list-products return 200 even if a DAOException is raised
     *
     * @throws Exception
     */
    @Test
    public void getListProductsWithDAOException() throws Exception {
        doThrow(DAOException.class).when(productService).getListProducts();
        mvc.perform(get(LIST_PRODUCTS).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    /**
     * Test that a request to show-product return 200
     *
     * @throws Exception
     */
    @Test
    public void showProduct() throws Exception {
        when(productService.getListProducts()).thenReturn(new ArrayList<Product>());
        mvc.perform(get(SHOW_PRODUCT).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    /**
     * Test that a request to show-product return 200 even if a DAOException is raised
     * 
     * @throws Exception
     */
    @Test
    public void showProductWithDAOException() throws Exception {
        doThrow(DAOException.class).when(productService).getListProducts();
        mvc.perform(get(SHOW_PRODUCT).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }
}
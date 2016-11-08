package com.drmeph.cartProject.configuration;

import com.drmeph.cartProject.controller.CartController;
import com.drmeph.cartProject.dao.impl.ProductDAOImpl;
import com.drmeph.cartProject.dao.impl.RatingDAOImpl;
import com.drmeph.cartProject.service.impl.CartServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.Mockito.mock;

/**
 * Created by kdorfer on 2016-11-07.
 */
@Configuration
public class TestCartConfiguration {

    @Bean(value = "cartService")
    CartServiceImpl getCartService() {
        return mock(CartServiceImpl.class);
    }

    @Bean
    @Qualifier(value = "testProductDAO")
    ProductDAOImpl getProductDAO() {
        return new ProductDAOImpl();
    }

    @Bean
    public JdbcTemplate getJdbcTemplate() {
        return mock(JdbcTemplate.class);
    }

    @Bean
    @Qualifier(value = "testRatingDAO")
    public RatingDAOImpl getTestRatingDAO() {
        return new RatingDAOImpl();
    }

    @Bean
    public CartController getCartController() {
        return new CartController();
    }

    @Bean(value = "messageSource")
    public MessageSource getMessageSource() {
        return mock(MessageSource.class);
    }
}

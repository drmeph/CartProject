package com.drmeph.cartProject.configuration;

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
 * Created by kdorfer on 2016-11-08.
 */
@Configuration
public class TestCartServiceConfiguration {

    @Bean
    @Qualifier("testCartService")
    CartServiceImpl getCartService() {
        return new CartServiceImpl();
    }

    @Bean(value = "productDAO")
    ProductDAOImpl getProductDAO() {
        return mock(ProductDAOImpl.class);
    }

    @Bean
    public JdbcTemplate getJdbcTemplate() {
        return mock(JdbcTemplate.class);
    }

    @Bean(value = "messageSource")
    public MessageSource getMessageSource() {
        return mock(MessageSource.class);
    }

    @Bean
    @Qualifier(value = "testRatingDAO")
    public RatingDAOImpl getTestRatingDAO() {
        return new RatingDAOImpl();
    }
}

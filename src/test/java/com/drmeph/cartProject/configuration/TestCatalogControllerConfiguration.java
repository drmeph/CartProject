package com.drmeph.cartProject.configuration;

import com.drmeph.cartProject.controller.CatalogController;
import com.drmeph.cartProject.dao.impl.ProductDAOImpl;
import com.drmeph.cartProject.dao.impl.RatingDAOImpl;
import com.drmeph.cartProject.service.impl.ProductServiceImpl;
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
public class TestCatalogControllerConfiguration {

    @Bean(value = "productService")
    ProductServiceImpl getProductService() {
        return mock(ProductServiceImpl.class);
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
    public CatalogController getCatalogController() {
        return new CatalogController();
    }

    @Bean(value = "messageSource")
    public MessageSource getMessageSource() {
        return mock(MessageSource.class);
    }
}

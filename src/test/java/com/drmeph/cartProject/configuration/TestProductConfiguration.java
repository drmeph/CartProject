package com.drmeph.cartProject.configuration;

import com.drmeph.cartProject.dao.impl.ProductDAOImpl;
import com.drmeph.cartProject.dao.impl.RatingDAOImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.Mockito.mock;

/**
 * Created by kdorfer on 2016-11-06.
 */
@Configuration
public class TestProductConfiguration {

    @Bean
    @Qualifier(value = "testProductDAO")
    public ProductDAOImpl getTestProductDAO() {
        return new ProductDAOImpl();
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

package com.drmeph.cartProject.configuration;

import com.drmeph.cartProject.dao.impl.RatingDAOImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.Mockito.mock;

/**
 * Created by kdorfer on 2016-11-08.
 */
@Configuration
public class TestRatingConfiguration {

    @Bean
    @Qualifier(value = "testRatingDAO")
    RatingDAOImpl getRatingDAO() {
        return new RatingDAOImpl();
    }

    @Bean
    public JdbcTemplate getJdbcTemplate() {
        return mock(JdbcTemplate.class);
    }

}

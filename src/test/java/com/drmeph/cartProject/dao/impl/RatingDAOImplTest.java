package com.drmeph.cartProject.dao.impl;

import com.drmeph.cartProject.configuration.TestRatingConfiguration;
import com.drmeph.cartProject.exception.DAOException;
import com.drmeph.cartProject.exception.MyDataAccessException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doThrow;

/**
 * Created by kdorfer on 2016-11-07.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestRatingConfiguration.class, loader = AnnotationConfigContextLoader.class)
public class RatingDAOImplTest {

    @Autowired
    RatingDAOImpl testRatingDAO;

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * Test that DAOException is thrown if request to database failed
     *
     * @throws Exception
     */
    @Test(expected = DAOException.class)
    public void addRatingWithDAOException() throws Exception {
        doThrow(new MyDataAccessException("KO")).when(jdbcTemplate).update(anyString(),
                new Object[] {anyInt(), anyInt()});

        testRatingDAO.addRating(1,10);
    }

}
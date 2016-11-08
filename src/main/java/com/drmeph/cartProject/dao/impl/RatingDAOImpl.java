package com.drmeph.cartProject.dao.impl;

import com.drmeph.cartProject.dao.RatingDAO;
import com.drmeph.cartProject.exception.DAOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Locale;

/**
 * Created by kdorfer on 2016-11-07.
 */
@Repository(value = "ratingDAO")
public class RatingDAOImpl implements RatingDAO {
    private static final String INSERT_NEW_RATING_SQL_REQUEST = "INSERT INTO cartproject.Rating (rate, productId) " +
            "VALUES  (?, ?)";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    MessageSource messageSource;

    /**
     *
     * @param productId
     * @param rating
     * @throws DAOException
     */
    @Override
    public void addRating(int productId, int rating) throws DAOException {
        Object[] params = new Object[] {rating, productId};

        try {
            jdbcTemplate.update(INSERT_NEW_RATING_SQL_REQUEST, params);
        } catch (DataAccessException e) {
            throw new DAOException(messageSource.getMessage("addRating.daoexception.message", null, Locale.ENGLISH));
        }
    }
}

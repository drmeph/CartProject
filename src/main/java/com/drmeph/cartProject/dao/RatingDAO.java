package com.drmeph.cartProject.dao;

import com.drmeph.cartProject.exception.DAOException;

/**
 * Created by kdorfer on 2016-11-07.
 */
public interface RatingDAO {
    void addRating(int productId, int rating) throws DAOException;
}

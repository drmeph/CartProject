package com.drmeph.cartProject.exception;

import org.springframework.dao.DataAccessException;

/**
 * Created by kdorfer on 2016-11-08.
 */
public class MyDataAccessException extends DataAccessException {

    public MyDataAccessException(String msg) {
        super(msg);
    }
}

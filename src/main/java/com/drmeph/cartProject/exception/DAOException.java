package com.drmeph.cartProject.exception;

/**
 * File: DAOException
 * Created by kevindorfer on 2016-06-19 at 7:06 PM
 */

public class DAOException extends Exception {
    private String message;

    public DAOException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

package com.my.booking.cinema.exception;

public class EntitySaveDaoException extends RuntimeException {
    public EntitySaveDaoException() {
    }

    public EntitySaveDaoException(String message) {
        super(message);
    }
}

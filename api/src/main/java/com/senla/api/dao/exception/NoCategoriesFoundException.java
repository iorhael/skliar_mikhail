package com.senla.api.dao.exception;

public class NoCategoriesFoundException extends RuntimeException {
    public NoCategoriesFoundException(String message) {
        super(message);
    }
}

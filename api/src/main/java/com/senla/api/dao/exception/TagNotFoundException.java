package com.senla.api.dao.exception;

public class TagNotFoundException extends RuntimeException {
    public TagNotFoundException(String message) {
        super(message);
    }
}

package com.senla.dao.exception;

public class TagNotFoundException extends RuntimeException {
    public TagNotFoundException(String message) {
        super(message);
    }
}

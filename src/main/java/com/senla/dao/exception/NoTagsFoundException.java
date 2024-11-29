package com.senla.dao.exception;

public class NoTagsFoundException extends RuntimeException {
    public NoTagsFoundException(String message) {
        super(message);
    }
}

package com.senla.api.dao.exception;

public class NoTagsFoundException extends RuntimeException {
    public NoTagsFoundException(String message) {
        super(message);
    }
}

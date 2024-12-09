package com.senla.api.dao.exception;

public class NoCommentsFoundException extends RuntimeException {
    public NoCommentsFoundException(String message) {
        super(message);
    }
}

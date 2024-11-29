package com.senla.dao.exception;

public class NoCommentsFoundException extends RuntimeException {
    public NoCommentsFoundException(String message) {
        super(message);
    }
}

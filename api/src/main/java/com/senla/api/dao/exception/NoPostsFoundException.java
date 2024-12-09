package com.senla.api.dao.exception;

public class NoPostsFoundException extends RuntimeException {
    public NoPostsFoundException(String message) {
        super(message);
    }
}

package com.senla.dao.exception;

public class NoPostsFoundException extends RuntimeException {
    public NoPostsFoundException(String message) {
        super(message);
    }
}

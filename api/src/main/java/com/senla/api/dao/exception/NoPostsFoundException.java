package com.senla.api.dao.exception;

public class NoPostsFoundException extends EntityNotFoundException {
    public NoPostsFoundException(String message) {
        super(message);
    }
}

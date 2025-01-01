package com.senla.repository.exception;

public class NoPostsFoundException extends EntityNotFoundException {
    public NoPostsFoundException(String message) {
        super(message);
    }
}

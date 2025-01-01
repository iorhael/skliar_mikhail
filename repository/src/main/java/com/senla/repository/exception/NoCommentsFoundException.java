package com.senla.repository.exception;

public class NoCommentsFoundException extends EntityNotFoundException {
    public NoCommentsFoundException(String message) {
        super(message);
    }
}

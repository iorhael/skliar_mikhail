package com.senla.api.dao.exception;

public class NoCommentsFoundException extends EntityNotFoundException {
    public NoCommentsFoundException(String message) {
        super(message);
    }
}

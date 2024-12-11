package com.senla.api.dao.exception;

public class PollNotFoundException extends RuntimeException {
    public PollNotFoundException(String message) {
        super(message);
    }
}

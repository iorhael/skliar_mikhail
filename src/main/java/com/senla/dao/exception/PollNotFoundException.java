package com.senla.dao.exception;

public class PollNotFoundException extends RuntimeException {
    public PollNotFoundException(String message) {
        super(message);
    }
}

package com.senla.api.dao.exception;

public class PollNotFoundException extends EntityNotFoundException {
    public PollNotFoundException(String message) {
        super(message);
    }
}

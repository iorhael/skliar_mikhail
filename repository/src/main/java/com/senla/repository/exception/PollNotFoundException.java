package com.senla.repository.exception;

public class PollNotFoundException extends EntityNotFoundException {
    public PollNotFoundException(String message) {
        super(message);
    }
}

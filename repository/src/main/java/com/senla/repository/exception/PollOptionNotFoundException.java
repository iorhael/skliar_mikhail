package com.senla.repository.exception;

public class PollOptionNotFoundException extends EntityNotFoundException {
    public PollOptionNotFoundException(String message) {
        super(message);
    }
}

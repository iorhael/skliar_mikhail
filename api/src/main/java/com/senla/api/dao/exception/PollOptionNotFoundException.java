package com.senla.api.dao.exception;

public class PollOptionNotFoundException extends EntityNotFoundException {
    public PollOptionNotFoundException(String message) {
        super(message);
    }
}

package com.senla.api.dao.exception;

public class PollOptionNotFoundException extends RuntimeException {
    public PollOptionNotFoundException(String message) {
        super(message);
    }
}

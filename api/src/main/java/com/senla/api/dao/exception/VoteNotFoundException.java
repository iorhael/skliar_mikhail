package com.senla.api.dao.exception;

public class VoteNotFoundException extends RuntimeException {
    public VoteNotFoundException(String message) {
        super(message);
    }
}

package com.senla.api.dao.exception;

public class VoteNotFoundException extends EntityNotFoundException {
    public VoteNotFoundException(String message) {
        super(message);
    }
}

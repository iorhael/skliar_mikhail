package com.senla.repository.exception;

public class VoteNotFoundException extends EntityNotFoundException {
    public VoteNotFoundException(String message) {
        super(message);
    }
}

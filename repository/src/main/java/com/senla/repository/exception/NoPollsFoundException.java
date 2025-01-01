package com.senla.repository.exception;

public class NoPollsFoundException extends EntityNotFoundException {
    public NoPollsFoundException(String message) {
        super(message);
    }
}

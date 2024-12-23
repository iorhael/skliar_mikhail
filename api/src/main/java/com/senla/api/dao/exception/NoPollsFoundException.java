package com.senla.api.dao.exception;

public class NoPollsFoundException extends EntityNotFoundException {
    public NoPollsFoundException(String message) {
        super(message);
    }
}

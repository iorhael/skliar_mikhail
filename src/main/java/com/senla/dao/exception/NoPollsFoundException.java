package com.senla.dao.exception;

public class NoPollsFoundException extends RuntimeException {
    public NoPollsFoundException(String message) {
        super(message);
    }
}

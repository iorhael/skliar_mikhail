package com.senla.dao.exception;

public class NoPollOptionsFoundException extends RuntimeException {
    public NoPollOptionsFoundException(String message) {
        super(message);
    }
}

package com.senla.api.dao.exception;

public class NoPollOptionsFoundException extends EntityNotFoundException {
    public NoPollOptionsFoundException(String message) {
        super(message);
    }
}

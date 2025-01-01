package com.senla.repository.exception;

public class NoPollOptionsFoundException extends EntityNotFoundException {
    public NoPollOptionsFoundException(String message) {
        super(message);
    }
}

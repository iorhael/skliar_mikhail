package com.senla.repository.exception;

public class NoTagsFoundException extends EntityNotFoundException {
    public NoTagsFoundException(String message) {
        super(message);
    }
}

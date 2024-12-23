package com.senla.api.dao.exception;

public class NoTagsFoundException extends EntityNotFoundException {
    public NoTagsFoundException(String message) {
        super(message);
    }
}

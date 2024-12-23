package com.senla.api.dao.exception;

public class TagNotFoundException extends EntityNotFoundException {
    public TagNotFoundException(String message) {
        super(message);
    }
}

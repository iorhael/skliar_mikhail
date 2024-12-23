package com.senla.api.dao.exception;

public class NoCategoriesFoundException extends EntityNotFoundException {
    public NoCategoriesFoundException(String message) {
        super(message);
    }
}

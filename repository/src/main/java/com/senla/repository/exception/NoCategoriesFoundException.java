package com.senla.repository.exception;

public class NoCategoriesFoundException extends EntityNotFoundException {
    public NoCategoriesFoundException(String message) {
        super(message);
    }
}

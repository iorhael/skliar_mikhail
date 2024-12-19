package com.senla.api.dao.exception;

public class CategoryNotFoundException extends EntityNotFoundException {
    public CategoryNotFoundException(String message) {
        super(message);
    }
}

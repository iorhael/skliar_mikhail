package com.senla.repository.exception;

public class UserNotFoundException extends EntityNotFoundException {
    public UserNotFoundException(String message) {
        super(message);
    }
}

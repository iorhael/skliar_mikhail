package com.senla.repository.exception;

public class NoUsersFoundException extends EntityNotFoundException {
    public NoUsersFoundException(String message) {
        super(message);
    }
}

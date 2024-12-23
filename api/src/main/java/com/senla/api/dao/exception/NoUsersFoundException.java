package com.senla.api.dao.exception;

public class NoUsersFoundException extends EntityNotFoundException {
    public NoUsersFoundException(String message) {
        super(message);
    }
}

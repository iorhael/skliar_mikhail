package com.senla.api.dao.exception;

public class NoUsersFoundException extends RuntimeException {
    public NoUsersFoundException(String message) {
        super(message);
    }
}

package com.senla.repository.exception;

public class NoRolesFoundException extends EntityNotFoundException {
    public NoRolesFoundException(String message) {
        super(message);
    }
}

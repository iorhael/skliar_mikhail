package com.senla.api.dao.exception;

public class NoRolesFoundException extends EntityNotFoundException {
    public NoRolesFoundException(String message) {
        super(message);
    }
}

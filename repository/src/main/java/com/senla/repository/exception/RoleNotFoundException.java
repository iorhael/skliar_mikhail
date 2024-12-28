package com.senla.repository.exception;

public class RoleNotFoundException extends EntityNotFoundException {
    public RoleNotFoundException(String message) {
        super(message);
    }
}

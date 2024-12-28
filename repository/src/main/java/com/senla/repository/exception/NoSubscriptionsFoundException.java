package com.senla.repository.exception;

public class NoSubscriptionsFoundException extends EntityNotFoundException {
    public NoSubscriptionsFoundException(String message) {
        super(message);
    }
}

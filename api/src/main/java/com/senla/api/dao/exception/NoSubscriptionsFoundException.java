package com.senla.api.dao.exception;

public class NoSubscriptionsFoundException extends EntityNotFoundException {
    public NoSubscriptionsFoundException(String message) {
        super(message);
    }
}

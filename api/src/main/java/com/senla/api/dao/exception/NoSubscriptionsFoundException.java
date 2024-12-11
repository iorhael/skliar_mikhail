package com.senla.api.dao.exception;

public class NoSubscriptionsFoundException extends RuntimeException {
    public NoSubscriptionsFoundException(String message) {
        super(message);
    }
}

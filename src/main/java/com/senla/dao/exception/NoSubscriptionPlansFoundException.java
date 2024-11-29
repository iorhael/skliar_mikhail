package com.senla.dao.exception;

public class NoSubscriptionPlansFoundException extends RuntimeException {
    public NoSubscriptionPlansFoundException(String message) {
        super(message);
    }
}

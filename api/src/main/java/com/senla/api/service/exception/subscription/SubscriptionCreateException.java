package com.senla.api.service.exception.subscription;

import com.senla.api.service.exception.ServiceException;

public class SubscriptionCreateException extends ServiceException {
    public SubscriptionCreateException(String message) {
        super(message);
    }
}

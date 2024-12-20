package com.senla.api.service.exception.subscription;

import com.senla.api.service.exception.ServiceException;

public class SubscriptionUpdateException extends ServiceException {
    public SubscriptionUpdateException(String message) {
        super(message);
    }
}

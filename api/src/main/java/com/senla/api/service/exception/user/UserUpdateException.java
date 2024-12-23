package com.senla.api.service.exception.user;

import com.senla.api.service.exception.ServiceException;

public class UserUpdateException extends ServiceException {
    public UserUpdateException(String message) {
        super(message);
    }
}

package com.senla.api.service.exception.user;

import com.senla.api.service.exception.ServiceException;

public class UserCreateException extends ServiceException {
    public UserCreateException(String message) {
        super(message);
    }
}

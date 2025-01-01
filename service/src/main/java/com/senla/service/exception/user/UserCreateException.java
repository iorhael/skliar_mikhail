package com.senla.service.exception.user;

import com.senla.service.exception.ServiceException;

public class UserCreateException extends ServiceException {
    public UserCreateException(String message) {
        super(message);
    }
}

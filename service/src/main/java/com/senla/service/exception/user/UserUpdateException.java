package com.senla.service.exception.user;

import com.senla.service.exception.ServiceException;

public class UserUpdateException extends ServiceException {
    public UserUpdateException(String message) {
        super(message);
    }
}

package com.senla.service.exception.user;

import com.senla.service.exception.ServiceException;

public class UserDeleteException extends ServiceException {
    public UserDeleteException(String message) {
        super(message);
    }
}

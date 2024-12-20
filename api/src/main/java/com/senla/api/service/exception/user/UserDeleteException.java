package com.senla.api.service.exception.user;

import com.senla.api.service.exception.ServiceException;

public class UserDeleteException extends ServiceException {
    public UserDeleteException(String message) {
        super(message);
    }
}

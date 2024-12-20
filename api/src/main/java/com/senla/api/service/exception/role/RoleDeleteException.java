package com.senla.api.service.exception.role;

import com.senla.api.service.exception.ServiceException;

public class RoleDeleteException extends ServiceException {
    public RoleDeleteException(String message) {
        super(message);
    }
}

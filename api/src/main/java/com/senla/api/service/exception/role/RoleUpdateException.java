package com.senla.api.service.exception.role;

import com.senla.api.service.exception.ServiceException;

public class RoleUpdateException extends ServiceException {
    public RoleUpdateException(String message) {
        super(message);
    }
}

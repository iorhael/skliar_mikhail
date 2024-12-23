package com.senla.api.service.exception.tag;

import com.senla.api.service.exception.ServiceException;

public class TagUpdateException extends ServiceException {
    public TagUpdateException(String message) {
        super(message);
    }
}

package com.senla.api.service.exception.tag;

import com.senla.api.service.exception.ServiceException;

public class TagCreateException extends ServiceException {
    public TagCreateException(String message) {
        super(message);
    }
}

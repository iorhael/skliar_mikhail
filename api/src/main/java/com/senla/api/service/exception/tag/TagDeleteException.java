package com.senla.api.service.exception.tag;

import com.senla.api.service.exception.ServiceException;

public class TagDeleteException extends ServiceException {
    public TagDeleteException(String message) {
        super(message);
    }
}

package com.senla.api.service.exception.post;

import com.senla.api.service.exception.ServiceException;

public class PostCreateException extends ServiceException {
    public PostCreateException(String message) {
        super(message);
    }
}

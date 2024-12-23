package com.senla.api.service.exception.comment;

import com.senla.api.service.exception.ServiceException;

public class CommentUpdateException extends ServiceException {
    public CommentUpdateException(String message) {
        super(message);
    }
}

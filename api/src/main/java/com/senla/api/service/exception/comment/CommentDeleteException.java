package com.senla.api.service.exception.comment;

import com.senla.api.service.exception.ServiceException;

public class CommentDeleteException extends ServiceException {
    public CommentDeleteException(String message) {
        super(message);
    }
}

package com.senla.api.service.exception.category;

import com.senla.api.service.exception.ServiceException;

public class CategoryDeleteException extends ServiceException {
    public CategoryDeleteException(String message) {
        super(message);
    }
}

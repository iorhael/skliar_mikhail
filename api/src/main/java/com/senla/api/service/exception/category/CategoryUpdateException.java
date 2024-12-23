package com.senla.api.service.exception.category;

import com.senla.api.service.exception.ServiceException;

public class CategoryUpdateException extends ServiceException {
    public CategoryUpdateException(String message) {
        super(message);
    }
}

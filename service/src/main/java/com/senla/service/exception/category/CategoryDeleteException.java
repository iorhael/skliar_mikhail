package com.senla.service.exception.category;


import com.senla.service.exception.ServiceException;

public class CategoryDeleteException extends ServiceException {
    public CategoryDeleteException(String message) {
        super(message);
    }
}

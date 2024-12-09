package com.senla.di.exception;

public class BeanPostProcessingException extends RuntimeException {
    public BeanPostProcessingException(String message) {
        super(String.format("Unable to configure bean: %s", message));
    }
}

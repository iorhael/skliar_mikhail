package com.senla.di.exception;

public class BeanPostProcessorRegistrationException extends RuntimeException {
    public BeanPostProcessorRegistrationException(String postProcessorType, String message) {
        super(String.format("Unable to register post processor with type %s. %s", postProcessorType, message));
    }
}

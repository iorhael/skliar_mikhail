package com.senla.api.util;

import com.senla.api.util.exception.ValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;

public final class ValidationUtil {
    private static final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private static final Validator validator = factory.getValidator();

    private ValidationUtil() {
    }

    public static <T> T validate(T object) {
        Set<ConstraintViolation<T>> violations = validator.validate(object);
        if (!violations.isEmpty()) {
            StringBuilder errorMessage = new StringBuilder("Validation errors:");
            for (ConstraintViolation<T> violation : violations) {
                errorMessage.append("\n").append(violation.getPropertyPath()).append(": ").append(violation.getMessage());
            }
            throw new ValidationException(errorMessage.toString());
        }
        return object;
    }
}

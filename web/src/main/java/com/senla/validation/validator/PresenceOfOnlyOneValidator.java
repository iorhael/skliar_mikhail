package com.senla.validation.validator;

import com.senla.validation.annotation.PresenceOfOnlyOne;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.Arrays;
import java.util.Objects;

public class PresenceOfOnlyOneValidator implements ConstraintValidator<PresenceOfOnlyOne, Object> {

    private String[] fields;

    @Override
    public void initialize(PresenceOfOnlyOne constraintAnnotation) {
        fields = constraintAnnotation.fields();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(value);

        return Arrays.stream(fields)
                .map(beanWrapper::getPropertyValue)
                .filter(Objects::nonNull)
                .count() == 1;
    }
}

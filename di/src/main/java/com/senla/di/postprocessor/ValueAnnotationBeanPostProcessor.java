package com.senla.di.postprocessor;

import com.senla.di.annotation.Value;
import com.senla.di.context.ApplicationContext;
import com.senla.di.exception.BeanPostProcessingException;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;

public class ValueAnnotationBeanPostProcessor implements BeanPostProcessor {

    private final Properties properties;

    public ValueAnnotationBeanPostProcessor() {
        properties = new Properties();
        try (var stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties")) {
            properties.load(stream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void configure(Object bean, ApplicationContext context) {
        Class<?> implementationClass = bean.getClass();
        for (Field field : implementationClass.getDeclaredFields()) {
            Value annotation = field.getAnnotation(Value.class);
            if (annotation != null) {
                String value = annotation.value().isEmpty() ? properties.getProperty(field.getName()) : properties.getProperty(annotation.value());
                field.setAccessible(true);
                try {
                    field.set(bean, value);
                } catch (IllegalAccessException e) {
                    throw new BeanPostProcessingException(e.getMessage());
                }
            }
        }
    }
}

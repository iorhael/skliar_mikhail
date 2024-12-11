package com.senla.di.postprocessor;

import com.senla.di.annotation.Autowired;
import com.senla.di.context.ApplicationContext;
import com.senla.di.exception.BeanPostProcessingException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class AutowiredAnnotationBeanPostProcessor implements BeanPostProcessor {

    @Override
    public void configure(Object bean, ApplicationContext context) {
        Class<?> implementationClass = bean.getClass();
        configureFields(bean, implementationClass, context);
        configureMethods(bean, implementationClass, context);
    }

    private void configureFields(Object bean, Class<?> implementationClass, ApplicationContext context) {
        for (Field field : implementationClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(Autowired.class)) {
                field.setAccessible(true);
                try {
                    field.set(bean, context.getBean(field.getType()));
                } catch (ReflectiveOperationException e) {
                    throw new BeanPostProcessingException(e.getMessage());
                }
            }
        }
    }

    private void configureMethods(Object bean, Class<?> implementationClass, ApplicationContext context) {
        for (Method method : implementationClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Autowired.class)) {
                method.setAccessible(true);

                Parameter[] parameters = method.getParameters();
                Object[] dependencies = new Object[parameters.length];

                for (int i = 0; i < parameters.length; i++) {
                    Parameter parameter = parameters[i];
                    Class<?> parameterType = parameter.getType();
                    Object dependency = context.getBean(parameterType);
                    dependencies[i] = dependency;
                }

                try {
                    method.invoke(bean, dependencies);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new BeanPostProcessingException(e.getMessage());
                }
            }
        }
    }
}

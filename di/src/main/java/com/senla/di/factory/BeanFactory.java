package com.senla.di.factory;

import com.senla.di.context.ApplicationContext;
import com.senla.di.exception.BeanCreationException;
import com.senla.di.exception.BeanPostProcessorRegistrationException;
import com.senla.di.postprocessor.BeanPostProcessor;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;

public class BeanFactory {
    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();
    private final ApplicationContext applicationContext;

    public BeanFactory(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        registerFrameworkBeanPostProcessors();
        registerClientBeanPostProcessors(applicationContext);
    }

    public <T> T createBean(Class<T> implemetationClass) {
        T bean = create(implemetationClass);

        configure(bean);

        return bean;
    }

    private void registerFrameworkBeanPostProcessors() {
        for (Class<? extends BeanPostProcessor> aClass : new Reflections("com.senla.di.postprocessor").getSubTypesOf(BeanPostProcessor.class)) {
            try {
                beanPostProcessors.add(aClass.getDeclaredConstructor().newInstance());
            } catch (ReflectiveOperationException e) {
                throw new BeanPostProcessorRegistrationException(aClass.getTypeName(), e.getMessage());
            }
        }
    }

    private void registerClientBeanPostProcessors(ApplicationContext applicationContext) {
        for (Class<? extends BeanPostProcessor> aClass : applicationContext.getBeanConfig().getScanner().getSubTypesOf(BeanPostProcessor.class)) {
            try {
                beanPostProcessors.add(aClass.getDeclaredConstructor().newInstance());
            } catch (ReflectiveOperationException e) {
                throw new BeanPostProcessorRegistrationException(aClass.getTypeName(), e.getMessage());
            }
        }
    }

    private <T> void configure(T bean) {
        beanPostProcessors.forEach(postProcessor -> postProcessor.configure(bean, applicationContext));
    }

    private <T> T create(Class<T> implementationClass) {
        try {
            return implementationClass.getDeclaredConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new BeanCreationException(implementationClass.getTypeName(), e.getMessage());
        }
    }
}

package com.senla.di.postprocessor;

import com.senla.di.context.ApplicationContext;

public interface BeanPostProcessor {
    void configure(Object bean, ApplicationContext context);
}

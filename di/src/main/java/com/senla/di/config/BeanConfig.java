package com.senla.di.config;

import org.reflections.Reflections;

public interface BeanConfig {
    <T> Class<? extends T> getImplementationClass(Class<T> interfaceClass);

    Reflections getScanner();
}

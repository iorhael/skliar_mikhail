package com.senla.di.config;

import org.reflections.Reflections;

import java.util.Set;

public class JavaBeanConfig implements BeanConfig {

    private final Reflections scanner;

    public JavaBeanConfig(String packageToScan) {
        this.scanner = new Reflections(packageToScan);
    }

    public Reflections getScanner() {
        return scanner;
    }

    @Override
    public <T> Class<? extends T> getImplementationClass(Class<T> interfaceClass) {
        Set<Class<? extends T>> implementationClasses = scanner.getSubTypesOf(interfaceClass);
        if (implementationClasses.size() != 1) {
            throw new RuntimeException("Found " + implementationClasses.size() + " implementations of " + interfaceClass.getName());
        }

        return implementationClasses.iterator().next();
    }
}

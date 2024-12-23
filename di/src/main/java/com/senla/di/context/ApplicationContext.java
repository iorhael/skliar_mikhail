package com.senla.di.context;

import com.senla.di.config.BeanConfig;
import com.senla.di.factory.BeanFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContext {

    private BeanFactory beanFactory;
    private final Map<Class<?>, Object> beanCache = new ConcurrentHashMap<>();
    private final BeanConfig beanConfig;

    public ApplicationContext(BeanConfig config) {
        this.beanConfig = config;
    }

    public BeanConfig getBeanConfig() {
        return beanConfig;
    }

    public Map<Class<?>, Object> getBeanCache() {
        return beanCache;
    }

    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public <T> T getBean(Class<T> clazz) {
        Class<? extends T> implementationClass = clazz;

        if (clazz.isInterface()) {
            implementationClass = beanConfig.getImplementationClass(clazz);
        }

        if (beanCache.containsKey(implementationClass)) {
            return clazz.cast(beanCache.get(implementationClass));
        }

        T bean = beanFactory.createBean(implementationClass);
        beanCache.put(implementationClass, bean);

        return bean;
    }
}

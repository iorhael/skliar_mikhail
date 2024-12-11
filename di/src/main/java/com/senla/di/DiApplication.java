package com.senla.di;

import com.senla.di.annotation.Component;
import com.senla.di.config.JavaBeanConfig;
import com.senla.di.context.ApplicationContext;
import com.senla.di.factory.BeanFactory;

import java.util.Set;

public class DiApplication {
    public static ApplicationContext run(Class<?> mainClass) {
        JavaBeanConfig beanConfigurator = new JavaBeanConfig(mainClass.getPackageName());
        ApplicationContext applicationContext = new ApplicationContext(beanConfigurator);
        BeanFactory beanFactory = new BeanFactory(applicationContext);
        applicationContext.setBeanFactory(beanFactory);

        initializeComponentBeans(beanConfigurator, applicationContext);

        return applicationContext;
    }

    private static void initializeComponentBeans(JavaBeanConfig beanConfigurator, ApplicationContext applicationContext) {
        Set<Class<?>> componentClasses = beanConfigurator.getScanner().getTypesAnnotatedWith(Component.class);
        for (Class<?> componentClass : componentClasses) {
            applicationContext.getBean(componentClass);
        }
    }
}

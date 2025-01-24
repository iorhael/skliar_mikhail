package com.senla;

import com.senla.config.RepositoryConfig;
import com.senla.config.ServiceConfig;
import com.senla.config.WebConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) {
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();

        rootContext.register(WebConfig.class, ServiceConfig.class, RepositoryConfig.class);

        servletContext.addListener(new ContextLoaderListener(rootContext));

        ServletRegistration.Dynamic appServlet =
                servletContext.addServlet("dispatcher", new DispatcherServlet(rootContext));
        appServlet.setLoadOnStartup(1);
        appServlet.addMapping("/");
    }
}

package com.senla;

import com.senla.di.DiApplication;
import com.senla.di.context.ApplicationContext;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.ServletRegistration;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpServlet;

@WebListener
public class ApplicationContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        ApplicationContext context = DiApplication.run(ApplicationContextListener.class);

        for (Object bean : context.getBeanCache().values()) {
            if (bean instanceof HttpServlet servlet) {
                String className = servlet.getClass().getSimpleName();

                ServletRegistration.Dynamic registration = servletContext.addServlet(className, servlet);
                registration.addMapping(String.format("/%s/*", className.replace("Servlet", "").toLowerCase()));
            }
        }
    }
}

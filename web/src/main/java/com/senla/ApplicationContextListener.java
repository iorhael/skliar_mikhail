package com.senla;

import com.senla.config.ApplicationConfiguration;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.ServletRegistration;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpServlet;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@WebListener
public class ApplicationContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

        for (Servlet servletBean : context.getBeansOfType(HttpServlet.class).values()) {
            String className = servletBean.getClass().getSimpleName();

            ServletRegistration.Dynamic registration = servletContext.addServlet(className, servletBean);
            registration.addMapping(String.format("/%s/*", className.replace("Servlet", "").toLowerCase()));
        }
    }
}

package com.example.vibeapp.config;

import jakarta.servlet.ServletRegistration;
import org.h2.server.web.JakartaWebServlet;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;

@Configuration
public class H2ConsoleConfig implements ServletContextInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        ServletRegistration.Dynamic h2Console = servletContext.addServlet("h2-console", new JakartaWebServlet());
        h2Console.setLoadOnStartup(1);
        h2Console.addMapping("/h2-console/*");
    }
}

package com.marwadi.bdr.web.servlet;

import com.marwadi.bdr.service.DonorService;
import org.apache.jasper.servlet.JspServlet;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ServletConfig {

    // Runs AuthFilter on every URL
    @Bean
    public FilterRegistrationBean<AuthFilter> authFilter() {
        FilterRegistrationBean<AuthFilter> reg = new FilterRegistrationBean<>();
        reg.setFilter(new AuthFilter());
        reg.setUrlPatterns(List.of("/*"));
        reg.setOrder(1);
        return reg;
    }

    // Opens the CSV download at /export/donors
    @Bean
    public ServletRegistrationBean<DonorExportServlet> donorExportServlet(DonorService donorService) {
        return new ServletRegistrationBean<>(new DonorExportServlet(donorService), "/export/donors");
    }

    // Starts counting sessions
    @Bean
    public ServletListenerRegistrationBean<SessionCounterListener> sessionCounterListener() {
        return new ServletListenerRegistrationBean<>(new SessionCounterListener());
    }

    // Registers the JSP engine so login.jsp and the other pages are run instead of returning 404
    @Bean
    public ServletRegistrationBean<JspServlet> jspServlet() {
        ServletRegistrationBean<JspServlet> reg = new ServletRegistrationBean<>(new JspServlet(), "*.jsp");
        reg.addInitParameter("development", "true");
        reg.setLoadOnStartup(3);
        return reg;
    }
}
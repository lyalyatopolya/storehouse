package com.example.storehouse.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;

public class UserLoginFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(UserLoginFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String userLogin = httpRequest.getHeader("user-login");

        if (userLogin != null) {
            logger.info("User '{}' made a request to: {} {}", 
                userLogin, 
                httpRequest.getMethod(), 
                httpRequest.getRequestURI());
        } else {
            logger.warn("Request without 'user-login' header detected!");
        }

        chain.doFilter(request, response);
    }
}
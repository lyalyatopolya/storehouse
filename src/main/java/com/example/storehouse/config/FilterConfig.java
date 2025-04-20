package com.example.storehouse.config;

import com.example.storehouse.controller.UserLoginFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<UserLoginFilter> userLoginFilter() {
        FilterRegistrationBean<UserLoginFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new UserLoginFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}
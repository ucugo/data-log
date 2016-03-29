package com.mahull.admin.config.mvc;

import com.mahull.admin.interceptor.UserLookupInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import static com.mahull.admin.util.Constants.BIRTHS_LOGIN;
import static com.mahull.admin.util.Constants.BIRTHS_REGISTER;
import static com.mahull.admin.util.Constants.DEATHS_LOGIN;
import static com.mahull.admin.util.Constants.DEATHS_REGISTER;
import static com.mahull.admin.util.Constants.HOME_VIEW;


@EnableAutoConfiguration
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private UserLookupInterceptor userLookupInterceptor;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        super.addViewControllers(registry);
        registry.addViewController("/home").setViewName(HOME_VIEW);
        registry.addViewController("/").setViewName(HOME_VIEW);
        registry.addViewController("/births/login").setViewName(BIRTHS_LOGIN);
        registry.addViewController("/deaths/login").setViewName(DEATHS_LOGIN);
        registry.addViewController("/births/register").setViewName(BIRTHS_REGISTER);
        registry.addViewController("/deaths/register").setViewName(DEATHS_REGISTER);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userLookupInterceptor);
    }
}

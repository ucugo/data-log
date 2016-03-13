package com.mahul.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import static com.mahul.util.Constants.HOME_VIEW;
import static com.mahul.util.Constants.LOGIN_VIEW;


@EnableAutoConfiguration
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        super.addViewControllers(registry);
        registry.addViewController("/home").setViewName(HOME_VIEW);
        registry.addViewController("/").setViewName(HOME_VIEW);
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/login").setViewName(LOGIN_VIEW);
    }

}

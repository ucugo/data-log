package com.mahul.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

/**
 * Created by Ugo on 29/02/2016.
 */

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        super.addViewControllers(registry);
//        registry.addViewController("/home").setViewName("home");
//        registry.addViewController("/").setViewName("home");
//        registry.addViewController("/hello").setViewName("hello");
//        registry.addViewController("/login").setViewName("login");
//    }


    @Bean
    public ServletContextTemplateResolver servletContextTemplateResolver() {
        ServletContextTemplateResolver servletContextTemplateResolver = new ServletContextTemplateResolver();
        servletContextTemplateResolver.setPrefix("/templates/");
        servletContextTemplateResolver.setSuffix(".html");
        servletContextTemplateResolver.setTemplateMode("HTML5");
        servletContextTemplateResolver.setCharacterEncoding("UTF-8");
//        default value is true. set to false if you want templates to update automatically when modified
        servletContextTemplateResolver.setCacheable(true);
        return servletContextTemplateResolver;
    }

    @Bean
    public SpringTemplateEngine springTemplateEngine(ServletContextTemplateResolver servletContextTemplateResolver) {
        SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
        springTemplateEngine.setTemplateResolver(servletContextTemplateResolver);
        return  springTemplateEngine;
    }


    @Bean
    public ThymeleafViewResolver thymeleafViewResolver(SpringTemplateEngine springTemplateEngine) {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(springTemplateEngine);
        viewResolver.setCharacterEncoding("UTF-8");
        viewResolver.setOrder(1);
        viewResolver.setViewNames(new String[]{"*.html,*.xhtml"});
        return viewResolver;
    }

}

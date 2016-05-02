package com.mahull.admin.config.mvc;

import com.mahull.admin.interceptor.CurrentRequestInterceptor;
import com.mahull.admin.interceptor.UserLookupInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import static com.mahull.admin.util.Constants.BIRTHS_LOGIN;
import static com.mahull.admin.util.Constants.BIRTHS_REGISTER;
import static com.mahull.admin.util.Constants.CONTEXT_ROOT;
import static com.mahull.admin.util.Constants.HOME_VIEW;
import static com.mahull.admin.util.Constants.ITEM;
import static com.mahull.admin.util.Constants.NEW_USER;


@EnableAutoConfiguration
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private UserLookupInterceptor userLookupInterceptor;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        super.addViewControllers(registry);
        registry.addViewController(CONTEXT_ROOT.concat(HOME_VIEW)).setViewName(HOME_VIEW);
        registry.addViewController(CONTEXT_ROOT).setViewName(HOME_VIEW);
        registry.addViewController(CONTEXT_ROOT.concat(BIRTHS_LOGIN)).setViewName(BIRTHS_LOGIN);
        registry.addViewController(CONTEXT_ROOT.concat(BIRTHS_REGISTER)).setViewName(BIRTHS_REGISTER);
        registry.addViewController(CONTEXT_ROOT.concat(NEW_USER)).setViewName(NEW_USER);
        registry.addViewController(CONTEXT_ROOT.concat(ITEM)).setViewName(ITEM);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userLookupInterceptor);
        registry.addInterceptor(new CurrentRequestInterceptor());
    }
}

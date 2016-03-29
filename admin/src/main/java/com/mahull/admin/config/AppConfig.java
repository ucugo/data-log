package com.mahull.admin.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.mahull.model", "com.mahull.admin"})
public class AppConfig {

}

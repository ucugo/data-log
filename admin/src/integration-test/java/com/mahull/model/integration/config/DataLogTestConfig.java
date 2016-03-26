package com.mahull.model.integration.config;

import com.mahull.admin.config.AppConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(AppConfig.class)
public class DataLogTestConfig {
}

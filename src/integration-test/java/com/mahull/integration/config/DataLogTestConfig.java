package com.mahull.integration.config;

import com.mahul.config.AppConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(AppConfig.class)
public class DataLogTestConfig {
}

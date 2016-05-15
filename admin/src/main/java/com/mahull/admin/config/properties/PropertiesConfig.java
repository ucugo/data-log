package com.mahull.admin.config.properties;

import com.mahull.admin.util.EnvironmentProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Ugo on 17/03/2016.
 */
@Configuration
@ComponentScan(basePackageClasses = {EnvironmentProperties.class})
public class PropertiesConfig {
}

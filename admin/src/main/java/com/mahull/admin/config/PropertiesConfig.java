package com.mahull.admin.config;

import com.mahull.admin.util.PropertiesFetcher;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Ugo on 17/03/2016.
 */
@Configuration
@ComponentScan(basePackageClasses = {PropertiesFetcher.class})
public class PropertiesConfig {
}

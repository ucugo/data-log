package com.mahull.admin;

import com.mahull.admin.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(AppConfig.class, args);
    }
}

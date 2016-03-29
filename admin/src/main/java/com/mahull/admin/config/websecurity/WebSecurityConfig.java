package com.mahull.admin.config.websecurity;

import com.mahull.admin.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by Ugo on 29/02/2016.
 */

@EnableWebSecurity
public class WebSecurityConfig {
    /**
     * Configures global settings for authentication.
     * @param auth authentication manager builder.
     * @param userDetailsService UserdetailsService Implementation class.
     * @param passwordEncoder Spring password encoder.
     * @throws Exception throws Exception.
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth,
                                UserDetailsServiceImpl userDetailsService,
                                PasswordEncoder passwordEncoder) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

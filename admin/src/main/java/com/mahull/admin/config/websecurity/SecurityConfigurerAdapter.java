package com.mahull.admin.config.websecurity;

import com.mahull.model.security.Role;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static com.mahull.admin.util.Constants.CONTEXT_ROOT;

/**
 * Created by Ugo on 29/03/2016.
 */
@Configuration
public class SecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/account/**")
                .hasAuthority(Role.USER_ROLE.name())
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage(CONTEXT_ROOT.concat("login"))
                .permitAll()
                .defaultSuccessUrl(CONTEXT_ROOT.concat("register"))
                .and()
                .logout()
                .permitAll();
    }

}

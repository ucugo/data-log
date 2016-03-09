package com.mahull.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

public class JPAConfigTest extends JPAConfig {

    @Bean
    public DataSource dataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost/data_log");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("147troublE");
        return dataSource;
    }

}
package com.mahull.config;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;


public class JPAConfigTest extends JPAConfig {

    @Bean
    public DataSource dataSource(){
//        DataSource dataSource = new DataSource();
//        dataSource.setUrl("jdbc:mysql://localhost/data_log");
//        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource.setUsername("root");
//        dataSource.setPassword("147troublE");
//        return dataSource;

        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .setName("data_log")
                .build();
    }

}
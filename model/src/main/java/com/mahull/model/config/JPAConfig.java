package com.mahull.model.config;

import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


/**
 * Created by Ugo on 05/03/2016.
 */
@Configuration
@EnableTransactionManagement
@ComponentScan({"com.mahull"})
@EntityScan(basePackages = {"com/mahull/repositories", "com/mahull/model"})
public class JpaConfig {

    @Bean
    public EntityManager entityManager(EntityManagerFactory entityManagerFactory) {
        return entityManagerFactory.createEntityManager();
    }
}

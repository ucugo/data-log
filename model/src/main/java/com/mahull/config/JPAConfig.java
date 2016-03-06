package com.mahull.config;

import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.dialect.MySQL5Dialect;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ugo on 05/03/2016.
 */
@Configuration
@EnableTransactionManagement
@ComponentScan({"com.mahull"})
public class JPAConfig {

    @Bean
    public EntityManagerFactory entityManagerFactory(EntityManagerFactoryBuilder builder,  DataSource dataSource){

        return builder.dataSource(dataSource)
                .packages("com.mahull.repositories", "com.mahull.model")
                .properties(properties())
                .build().getObject();
    }

    @Bean
    public DataSource dataSource(){
        LazyConnectionDataSourceProxy lazyConnectionDataSourceProxy = new LazyConnectionDataSourceProxy();
        Context ctx;
        try {
            ctx = new InitialContext();
            lazyConnectionDataSourceProxy.setTargetDataSource((DataSource) ctx.lookup("java:comp/env/jdbc/inventory"));
        } catch (NamingException e) {
            throw new InternalError("Error in retrieving Context");
        }


        return lazyConnectionDataSourceProxy;
    }



    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) throws NamingException {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        transactionManager.setPersistenceUnitName("inventory");
        return transactionManager;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter(){
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.getJpaPropertyMap().put("database","MYSQL");
        adapter.getJpaPropertyMap().put("generateDdl", "true");
        adapter.getJpaPropertyMap().put("showSql", "true");
        return adapter;
    }

    @Bean
    public HibernateExceptionTranslator hibernateExceptionTranslator(){
        HibernateExceptionTranslator hibernateExceptionTranslator = new HibernateExceptionTranslator();
        return hibernateExceptionTranslator;
    }

    public Map<String, Object> properties(){
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.format_sql",true);
        properties.put("hibernate.hbm2ddl.auto","update");
        properties.put("hibernate.implicit_naming_strategy",new ImplicitNamingStrategyJpaCompliantImpl());
        properties.put("hibernate.dialect",new MySQL5Dialect());
        return properties;
    }

    @Bean
    public EntityManager entityManager(EntityManagerFactory entityManagerFactory) {
        return entityManagerFactory.createEntityManager();
    }
}

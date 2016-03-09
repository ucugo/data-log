package com.mahull.config;

import com.mahull.hibernate.HibernateNamingStrategy;
import org.hibernate.dialect.MySQL5Dialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Properties;

import org.apache.tomcat.jdbc.pool.DataSource;

/**
 * Created by Ugo on 05/03/2016.
 */
@Configuration
@EnableTransactionManagement
@ComponentScan({"com.mahull"})
public class JPAConfig {

    private static final String PERSISTENCE_UNIT_NAME = "data_log";

    @Bean
    public EntityManagerFactory entityManagerFactory(javax.sql.DataSource dataSource){
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        final LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setJpaVendorAdapter(vendorAdapter);
        bean.setDataSource(dataSource);
        bean.setPackagesToScan("com/mahull/repositories", "com/mahull/model");
        bean.afterPropertiesSet();
        bean.setPersistenceUnitName("data_log");
        bean.setJpaProperties(properties());
        bean.setPersistenceXmlLocation("META-INF/persistence.xml");
        return bean.getObject();
    }
    @Bean
    public javax.sql.DataSource dataSource(){
        DataSource dataSource;
        Context ctx;
        try {
            ctx = new InitialContext();
            dataSource =((DataSource) ctx.lookup("java:comp/env/jdbc/inventory"));
        } catch (NamingException e) {
            throw new InternalError(String.format("Error in retrieving Context: %s", e.getCause()));
        }
        return dataSource;
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) throws NamingException {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        transactionManager.setPersistenceUnitName(PERSISTENCE_UNIT_NAME);
        return transactionManager;
    }

    public Properties properties(){
        Properties properties = new Properties();
        properties.put("hibernate.format_sql",true);
        properties.put("hibernate.hbm2ddl.auto","update");
        properties.put("hibernate.ejb.naming_strategy",new HibernateNamingStrategy());
        properties.put("hibernate.dialect",new MySQL5Dialect());
        return properties;
    }

    @Bean
    public EntityManager entityManager(EntityManagerFactory entityManagerFactory) {
        return entityManagerFactory.createEntityManager();
    }
}

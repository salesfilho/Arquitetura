/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.prof.salesfilho.arq.persistence.config;

import com.google.common.base.Preconditions;
import java.util.Properties;
import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author salesfilho
 */
@Configuration
@EnableTransactionManagement
public class PersistenceJPAConfig {

    @Inject
    private Properties dataBaseProperties;
    @Inject
    private String packageToScan;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(packageToScan);

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

        em.setJpaVendorAdapter(vendorAdapter);
        em.setPersistenceUnitName(Preconditions.checkNotNull(dataBaseProperties.getProperty("persistenceUnitName")));
        em.setJpaProperties(getAdditionalProperties());

        return em;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Preconditions.checkNotNull(dataBaseProperties.getProperty("jdbc.driverClassName")));
        dataSource.setUrl(Preconditions.checkNotNull(dataBaseProperties.getProperty("jdbc.url")));
        dataSource.setUsername(Preconditions.checkNotNull(dataBaseProperties.getProperty("jdbc.user")));
        dataSource.setPassword(Preconditions.checkNotNull(dataBaseProperties.getProperty("jdbc.pass")));

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);

        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    private Properties getAdditionalProperties() {
        Properties properties = new Properties();

        properties.setProperty("hibernate.dialect", dataBaseProperties.getProperty("hibernate.dialect"));
        properties.setProperty("hibernate.cache.provider_class", dataBaseProperties.getProperty("hibernate.cache.provider_class"));
        properties.setProperty("hibernate.show_sql", dataBaseProperties.getProperty("hibernate.show_sql"));
        properties.setProperty("hibernate.format_sql", dataBaseProperties.getProperty("hibernate.format_sql"));
        properties.setProperty("hbm2ddl.auto", dataBaseProperties.getProperty("hbm2ddl.auto"));
        return properties;
    }

}

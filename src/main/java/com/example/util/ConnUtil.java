package com.example.util;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.example.config.ConnConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ConnUtil {

    public Map<String, Object> getJpaProperties(String dialect) {
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", dialect);

        return properties;
    }

    public DataSource getDatasource(ConnConfig config) {
        HikariDataSource hikari = new HikariDataSource();

        hikari.setJdbcUrl(config.getUrl());
        hikari.setUsername(config.getUser());
        hikari.setPassword(config.getPass());
        hikari.setDriverClassName(config.getDriver());
        hikari.setMinimumIdle(config.getMinIdle());
        hikari.setIdleTimeout(config.getIdleTimeout());
        hikari.setMaxLifetime(config.getMaxLifetime());
        hikari.setMaximumPoolSize(config.getMaxPoolSize());
        hikari.setConnectionTimeout(config.getConnectionTimeout());

        return hikari;
    }

    public LocalContainerEntityManagerFactoryBean getEntityManagerFactory(DataSource datasource,
            Map<String, Object> jpaProperties, String name) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(datasource);
        em.setPackagesToScan("com.example.entities." + name);

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaPropertyMap(jpaProperties);

        return em;
    }

    public PlatformTransactionManager getTransactionManager(
            LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory.getObject());

        return transactionManager;
    }

    public JdbcTemplate getJdbcTemplate(ConnConfig config) {
        return new JdbcTemplate(getDatasource(config));
    }
    
}

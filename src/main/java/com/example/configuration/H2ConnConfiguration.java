package com.example.configuration;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.example.config.H2ConnConfig;
import com.example.util.ConnUtil;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = H2ConnConfiguration.NAME + "EntityManagerFactory",
        transactionManagerRef = H2ConnConfiguration.NAME + "TransactionManager",
        basePackages = { "com.example.repositories." + H2ConnConfiguration.NAME })
public class H2ConnConfiguration {

    public static final String NAME = "h2conn";
    private final H2ConnConfig config;

    private Map<String, Object> getJpaProperties() {
        return ConnUtil.getJpaProperties(config.getDialect());
    }

    @Bean(NAME + "Datasource")
    public DataSource getDatasource() {
        return ConnUtil.getDatasource(config);
    }

    @Bean(NAME + "EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactory() {
        return ConnUtil.getEntityManagerFactory(getDatasource(), getJpaProperties(), NAME);
    }

    @Bean(NAME + "TransactionManager")
    public PlatformTransactionManager transactionManager() {
        return ConnUtil.getTransactionManager(getEntityManagerFactory());
    }

    @Bean(NAME + "JdbcTemplate")
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(getDatasource());
    }

}

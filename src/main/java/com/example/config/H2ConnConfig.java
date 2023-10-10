package com.example.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "h2conn.datasource")
public class H2ConnConfig implements ConnConfig {

    private String url;
    private String user;
    private String pass;
    private String driver = "org.h2.Driver";
    private String dialect = "org.hibernate.dialect.H2Dialect";
    
    private int minIdle = 1;
    private long idleTimeout = 60000;
    private int maxPoolSize = 5;
    private long connectionTimeout = 60000;
    private long maxLifetime = 1800000;

}

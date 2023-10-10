package com.example.config;

public interface ConnConfig {

    String getUrl();
    String getUser();
    String getPass();
    String getDriver();
    String getDialect();

    int getMinIdle();
    long getIdleTimeout();
    long getMaxLifetime();
    int getMaxPoolSize();
    long getConnectionTimeout();

}

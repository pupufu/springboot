package com.ppf.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
@Configuration
public class TransactionConfig {

    @Autowired
    private DataSource dataSource;

    @Bean("txManager")
    public DataSourceTransactionManager txManager() {
        return new DataSourceTransactionManager(dataSource);
    }

}

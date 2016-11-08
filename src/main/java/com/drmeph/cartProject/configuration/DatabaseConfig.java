package com.drmeph.cartProject.configuration;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by kdorfer on 2016-11-06.
 */
@Configuration
public class DatabaseConfig {
    private static final String JDBC_DRIVER_NAME = "com.mysql.jdbc.Driver";

    @Value("${url}")
    private String databaseUrl;

    @Value("${username}")
    private String username;

    @Value("${password}")
    private String password;

    @Bean
    public DataSource dataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(JDBC_DRIVER_NAME);
        ds.setUrl(databaseUrl);
        ds.setUsername(username);
        ds.setPassword(password);

        return ds;
    }

    @Bean
    @Autowired
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}

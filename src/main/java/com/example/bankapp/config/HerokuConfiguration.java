package com.example.bankapp.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.net.URISyntaxException;

@Configuration
@Component
public class HerokuConfigCloud {

    private static final Logger logger =
            LoggerFactory.getLogger(HerokuConfigCloud .class);

    @Bean()
//@Primary this annotation to be used if more than one DB Config was used.  In that case,
// using @Primary would give precedence to a the particular "primary" config class
    @Profile("heroku")
    public DataSource dataSource(
            @Value("${spring.datasource.driverClassName}") final String driverClass,
            @Value("${spring.datasource.url}") final String jdbcUrl,
            @Value("${spring.datasource.username}") final String username,
            @Value("${spring.datasource.password}") final String password
    ) throws URISyntaxException {


        return DataSourceBuilder
                .create()
                .username(username)
                .password(password)
                .url(jdbcUrl)
                .driverClassName(driverClass)
                .build();
    }
}

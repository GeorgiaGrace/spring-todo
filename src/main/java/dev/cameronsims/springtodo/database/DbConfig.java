package dev.cameronsims.springtodo.database;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbConfig {

    @Bean
    DataSource dataSource() {

        DataSourceBuilder builder = DataSourceBuilder.create();

        return builder
            .driverClassName("org.postgresql.Driver")
            .url("jdbc:postgresql://localhost:5432/spring-todo")
            .username("postgres")
            .password("Notime2die@")
            .build();

    }
    
}

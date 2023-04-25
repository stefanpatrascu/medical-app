package com.medical.medicalappointments.security.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import javax.sql.DataSource;

@Configuration
public class FlywayConfig {

    @Value("${spring.datasource.url}")
    private String datasourceUrl;

    @Value("${spring.datasource.username}")
    private String datasourceUsername;

    @Value("${spring.datasource.password}")
    private String datasourcePassword;

    @Value("${spring.datasource.driver-class-name}")
    private String datasourceDriverClassName;

    @Bean
    @FlywayDataSource
    public DataSource flywayDataSource() {
        return DataSourceBuilder.create()
            .url(datasourceUrl)
            .username(datasourceUsername)
            .password(datasourcePassword)
            .driverClassName(datasourceDriverClassName)
            .build();
    }

    @Bean
    public Flyway flyway() {
        return Flyway.configure()
            .dataSource(flywayDataSource())
            .load();
    }

    @Bean
    public ApplicationListener<ContextRefreshedEvent> runFlywayMigration(Flyway flyway) {
        return event -> {
            flyway.migrate();
        };
    }
}

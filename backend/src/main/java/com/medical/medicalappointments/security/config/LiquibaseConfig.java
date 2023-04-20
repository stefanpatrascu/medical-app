package com.medical.medicalappointments.security.config;

import liquibase.exception.LiquibaseException;
import liquibase.integration.spring.SpringLiquibase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import javax.sql.DataSource;

@Configuration
public class LiquibaseConfig implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(LiquibaseConfig.class);

    private final SpringLiquibase liquibase;

    public LiquibaseConfig(DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog("classpath:db/changelog/db.changelog.xml");
        liquibase.setShouldRun(true);
        this.liquibase = liquibase;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            liquibase.afterPropertiesSet(); // Execută Liquibase după inițializarea aplicației
            logger.info("Liquibase runned successfully");
        } catch (LiquibaseException e) {
            logger.error("Fail to run Liquibase", e);
        }
    }
}

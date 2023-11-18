package com.skillbox.users.support;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.DockerImageName;

@ActiveProfiles("test")
@ContextConfiguration(initializers = PostgreSQLInitializer.class)
public class PostgreSQLInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private static final DockerImageName IMAGE = DockerImageName.parse("postgres:16.0-alpine");
    private static final PostgreSQLContainer<?> POSTGRE_SQL_CONTAINER = new PostgreSQLContainer<>(IMAGE)
            .withUsername("test-user")
            .withPassword("test-password")
            .withDatabaseName("users")
            .waitingFor(Wait.forListeningPort());

    @Override
    public void initialize(final ConfigurableApplicationContext applicationContext) {
        POSTGRE_SQL_CONTAINER.start();
        TestPropertyValues.of(
                "spring.datasource.url=" + POSTGRE_SQL_CONTAINER.getJdbcUrl(),
                "spring.datasource.username=" + POSTGRE_SQL_CONTAINER.getUsername(),
                "spring.datasource.password=" + POSTGRE_SQL_CONTAINER.getPassword(),
                "flyway.user=", POSTGRE_SQL_CONTAINER.getUsername(),
                "flyway.password=", POSTGRE_SQL_CONTAINER.getPassword()
        ).applyTo(applicationContext.getEnvironment());
    }
}

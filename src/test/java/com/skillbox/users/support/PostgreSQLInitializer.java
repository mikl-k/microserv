package com.skillbox.users.support;


import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.annotation.DirtiesContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.Duration;

@Testcontainers
@DirtiesContext
public class PostgreSQLInitializer {

    @Container
    @ServiceConnection
    private static final PostgreSQLContainer<?> POSTGRE_SQL_CONTAINER =
            new PostgreSQLContainer<>("postgres:16.0-alpine")
                    .withUsername("test-user1")
                    .withPassword("test-password1")
                    .withMinimumRunningDuration(Duration.ofSeconds(1));

}

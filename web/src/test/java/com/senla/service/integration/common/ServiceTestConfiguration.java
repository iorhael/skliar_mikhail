package com.senla.service.integration.common;

import com.senla.config.ServiceConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.PostgreSQLContainer;

@TestConfiguration
@Import({ServiceConfig.class})
@ComponentScan(basePackages = {"com.senla.config.modelmapper"})
public class ServiceTestConfiguration {

    @Bean
    @ServiceConnection
    public PostgreSQLContainer<?> postgreSQLContainer(@Value("${application.postgres-image}")
                                                          String postgresImage) {
        return new PostgreSQLContainer<>(postgresImage);
    }
}

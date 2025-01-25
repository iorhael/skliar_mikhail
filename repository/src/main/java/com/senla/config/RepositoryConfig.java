package com.senla.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.senla.config", "com.senla.repository"})
public class RepositoryConfig {
}

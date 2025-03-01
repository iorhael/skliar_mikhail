package com.senla.service.integration.common;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

@DataJpaTest
@ContextConfiguration(classes = ServiceTestConfiguration.class)
public abstract class CommonServiceTest {
}

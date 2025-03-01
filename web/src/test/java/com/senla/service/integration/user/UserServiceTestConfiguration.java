package com.senla.service.integration.user;

import com.senla.repository.UserRepository;
import com.senla.service.UserService;
import com.senla.service.imp.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@TestConfiguration
public class UserServiceTestConfiguration {

    @Bean
    UserService userService(UserRepository repository,
                            ModelMapper mapper,
                            PasswordEncoder encoder) {
        return new UserServiceImpl(repository, mapper, encoder);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

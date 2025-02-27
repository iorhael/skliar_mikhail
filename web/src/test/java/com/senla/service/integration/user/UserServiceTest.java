package com.senla.service.integration.user;

import com.senla.dto.user.UserCreateDto;
import com.senla.dto.user.UserGetDto;
import com.senla.dto.user.UserWithRolesDto;
import com.senla.model.User;
import com.senla.repository.UserRepository;
import com.senla.service.UserService;
import com.senla.service.integration.common.CommonServiceTest;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = UserServiceTestConfiguration.class)
class UserServiceTest extends CommonServiceTest {
    static final String STRING_PLACEHOLDER = "Test";
    static final String VALID_MAIL = "test@test.com";

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    TestEntityManager entityManager;

    User user;

    @BeforeEach
    void setup() {
        user = new User();
        user.setUsername(STRING_PLACEHOLDER);
        user.setEmail(STRING_PLACEHOLDER);
        user.setPassword(STRING_PLACEHOLDER);

        userRepository.save(user);
    }

    @Test
    void givenCorrectUser_whenCreateUser_thenReturnUserWithRolesDto() {
        UserCreateDto createDto = new UserCreateDto();
        createDto.setUsername("John");
        createDto.setEmail(VALID_MAIL);
        createDto.setPassword(STRING_PLACEHOLDER);

        assertThat(userService.createUser(createDto))
                .isInstanceOf(UserWithRolesDto.class);
    }

    @Test
    void givenExistingUsername_whenCreateUser_thenThrowDataIntegrityViolationException() {
        UserCreateDto createDto = new UserCreateDto();
        createDto.setUsername(STRING_PLACEHOLDER);
        createDto.setEmail("test1@test.com");
        createDto.setPassword(STRING_PLACEHOLDER);

        assertThrows(DataIntegrityViolationException.class,
                () -> userService.createUser(createDto));
    }

    @Test
    void givenExistingEmail_whenCreateUser_thenThrowDataIntegrityViolationException() {
        UserCreateDto createDto = new UserCreateDto();
        createDto.setUsername("John");
        createDto.setEmail(STRING_PLACEHOLDER);
        createDto.setPassword(STRING_PLACEHOLDER);

        assertThrows(DataIntegrityViolationException.class,
                () -> userService.createUser(createDto));
    }

    @Test
    void givenUser_whenGetUserById_thenReturnUserGetDto() {
        UserGetDto dto = userService.getUserBy(user.getId());

        assertNotNull(dto);
        assertEquals(STRING_PLACEHOLDER, dto.getUsername());
        assertEquals(STRING_PLACEHOLDER, dto.getEmail());
    }

    @Test
    void givenNoUser_whenGetUserById_thenThrowUserNotFoundException() {
        UUID id = UUID.randomUUID();

        assertThrows(EntityNotFoundException.class,
                () -> userService.getUserBy(id));
    }

    @Test
    void whenUpdatePassword() {
        UUID id = user.getId();
        String newPassword = "newPassword";

        userService.updatePassword(newPassword, id);
        entityManager.refresh(user);

        assertTrue(passwordEncoder.matches(newPassword, user.getPassword()));
    }

    @Test
    void whenDeleteUser() {
        userService.deleteUser(user.getId());

        assertThat(userRepository.findAll()).isEmpty();
    }
}

package com.senla.controller;

import com.senla.controller.dto.ResponseInfoDto;
import com.senla.dto.user.UserCreateDto;
import com.senla.dto.user.UserGetDto;
import com.senla.service.PollOptionService;
import com.senla.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private static final String USER_DELETION_MESSAGE = "User with id %s deleted successfully";

    private final UserService userService;

    private final PollOptionService pollOptionService;

    @GetMapping
    public List<UserGetDto> findAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserGetDto findUserById(@PathVariable UUID id) {
        return userService.getUserBy(id);
    }

    @GetMapping(params = "email")
    public UserGetDto findUserByEmail(@RequestParam String email) {
        return userService.getUserBy(email);
    }

    @GetMapping(params = "username")
    public List<UserGetDto> findUsersByUsername(@RequestParam String username) {
        return userService.getUsersBy(username);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserGetDto createUser(@Valid @RequestBody UserCreateDto user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public UserGetDto updateUser(@Valid @RequestBody UserCreateDto user,
                                 @PathVariable UUID id) {
        return userService.updateUser(user, id);
    }

    @DeleteMapping("/{id}")
    public ResponseInfoDto deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);

        return ResponseInfoDto.builder()
                .message(String.format(USER_DELETION_MESSAGE, id))
                .build();
    }
}

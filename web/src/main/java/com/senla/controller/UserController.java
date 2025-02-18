package com.senla.controller;

import com.senla.controller.dto.ResponseInfoDto;
import com.senla.dto.user.PasswordDto;
import com.senla.dto.user.PasswordUpdateDto;
import com.senla.dto.user.UserGetDto;
import com.senla.dto.user.UserUpdateDto;
import com.senla.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private static final String USER_DELETION_MESSAGE = "Account deleted successfully";
    private static final String PASSWORD_UPDATED_MESSAGE = "Password updated successfully";

    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserGetDto> findAllUsers(@RequestParam(defaultValue = "0") int pageNo,
                                         @RequestParam(defaultValue = "15") int pageSize) {
        return userService.getAllUsers(pageNo, pageSize);
    }

    @GetMapping(params = "username")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserGetDto> findUsersByUsername(@RequestParam(defaultValue = "0") int pageNo,
                                                @RequestParam(defaultValue = "15") int pageSize,
                                                @RequestParam String username) {
        return userService.getUsersBy(username, pageNo, pageSize);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public UserGetDto findUserById(@PathVariable UUID id) {
        return userService.getUserBy(id);
    }

    @PutMapping
    public UserGetDto updateUser(@Valid @RequestBody UserUpdateDto user,
                                 Principal principal) {
        return userService.updateUser(user, principal.getName());
    }

    @PutMapping("/password")
    public ResponseInfoDto updatePassword(@Valid @RequestBody PasswordUpdateDto passwordUpdateDto,
                                          Principal principal) {
        userService.updatePassword(passwordUpdateDto, principal.getName());

        return ResponseInfoDto.builder()
                .message(PASSWORD_UPDATED_MESSAGE)
                .build();
    }

    @DeleteMapping
    public ResponseInfoDto deleteUser(@Valid @RequestBody PasswordDto passwordDto,
                                      Principal principal) {
        userService.deleteUser(passwordDto.getPassword(), principal.getName());

        return ResponseInfoDto.builder()
                .message(USER_DELETION_MESSAGE)
                .build();
    }
}

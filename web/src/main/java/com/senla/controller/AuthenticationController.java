package com.senla.controller;

import com.senla.dto.AuthenticationResponseDto;
import com.senla.dto.LoginDto;
import com.senla.dto.user.UserCreateDto;
import com.senla.service.authentication.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public AuthenticationResponseDto login(@Valid @RequestBody LoginDto loginDto) {
        return authenticationService.login(loginDto);
    }

    @PostMapping("/signup")
    public AuthenticationResponseDto signUp(@Valid @RequestBody UserCreateDto userCreateDto) {
        return authenticationService.signUp(userCreateDto);
    }
}

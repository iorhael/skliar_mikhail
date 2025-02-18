package com.senla.service.authentication;

import com.senla.dto.AuthenticationResponseDto;
import com.senla.dto.LoginDto;
import com.senla.dto.user.UserCreateDto;

public interface AuthenticationService {

    AuthenticationResponseDto signUp(UserCreateDto user);

    AuthenticationResponseDto login(LoginDto loginDto);
}

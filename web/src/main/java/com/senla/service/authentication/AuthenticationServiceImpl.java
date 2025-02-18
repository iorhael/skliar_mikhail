package com.senla.service.authentication;

import com.senla.dto.AuthenticationResponseDto;
import com.senla.dto.LoginDto;
import com.senla.dto.user.UserCreateDto;
import com.senla.dto.user.UserWithRolesDto;
import com.senla.service.UserService;
import com.senla.service.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponseDto signUp(UserCreateDto user) {
        UserWithRolesDto dto = userService.createUser(user);

        return new AuthenticationResponseDto(jwtService.generateToken(
                dto.getUsername(),
                dto.getRoles())
        );
    }

    @Override
    public AuthenticationResponseDto login(LoginDto loginDto) {
        String principal = extractUserIdentifier(loginDto);

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                principal,
                loginDto.getPassword())
        );

        return new AuthenticationResponseDto(jwtService.generateToken(
                authentication.getName(),
                getAuthorities(authentication))
        );
    }

    private String extractUserIdentifier(LoginDto loginDto) {
        return Objects.nonNull(loginDto.getUsername())
                ? loginDto.getUsername()
                : loginDto.getEmail();
    }

    private Set<String> getAuthorities(Authentication authentication) {
        return authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());
    }
}

package com.senla.service.jwt;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;
import java.util.UUID;

public interface JwtService {

    String generateToken(UUID userId, Set<String> roles);

    Claims extractClaims(String token);

    UUID getUserId(Claims claims);

    Set<GrantedAuthority> getRoles(Claims claims);
}

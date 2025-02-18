package com.senla.service.jwt;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

public interface JwtService {

    String generateToken(String subject, Set<String> roles);

    Claims extractClaims(String token);

    String getPrincipal(Claims claims);

    Set<GrantedAuthority> getRoles(Claims claims);
}

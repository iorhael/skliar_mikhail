package com.senla.service.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class JwtServiceImpl implements JwtService {
    public static final String ROLE_PREFIX = "ROLE_";
    private static final String ROLES_CLAIM_NAME = "roles";

    @Value("${application.security.jwt.secret-key}")
    private String secretKey;

    @Value("${application.security.jwt.expiration}")
    private Long expiration;

    @Override
    public String generateToken(UUID userId, Set<String> roles) {
        return Jwts.builder()
                .id(UUID.randomUUID().toString())
                .subject(userId.toString())
                .claim(ROLES_CLAIM_NAME, roles)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignKey())
                .compact();
    }

    @Override
    public Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    @Override
    public UUID getUserId(Claims claims) {
        return UUID.fromString(claims.getSubject());
    }

    @Override
    @SuppressWarnings("unchecked")
    public Set<GrantedAuthority> getRoles(Claims claims) {
        List<String> roles = claims.get(ROLES_CLAIM_NAME, List.class);

        return roles.stream()
                .map(ROLE_PREFIX::concat)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }

    private SecretKey getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

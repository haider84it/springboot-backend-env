package com.example.pvbackend.security;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;


@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretKey;

    // ✅ Generate a JWT token
    public String generateToken(String email, String role) {
        return Jwts.builder()
                .setSubject(email) // The email is the "username"
                .claim("role", role) // ROLE_ADMIN or ROLE_USER
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // Token valid for 1 day
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // ✅ Extract all claims
    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // ✅ Extract email from token
    public String extractEmail(String token) {
        return extractClaims(token).getSubject();
    }

    // ✅ Extract role from token
    public String extractRole(String token) {
        return extractClaims(token).get("role", String.class);
    }

    // ✅ Check if token is valid
    public boolean isTokenValid(String token) {
        try {
            Claims claims = extractClaims(token);
            return claims.getExpiration().after(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // ✅ Key builder (always use at least 32-byte secret key)
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }
}

package com.example.orderservice.utils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.enterprise.context.ApplicationScoped;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@ApplicationScoped
public class Jwt {

    private static final String JWT_SECRET = "the-most-secure-secret-key-in-hostory";
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(
            JWT_SECRET.getBytes(StandardCharsets.UTF_8));

    public static Claims validateToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public static String getUsername(String token) {
        return validateToken(token).getSubject();
    }

    public static String getRole(String token) {
        return validateToken(token).get("role", String.class);
    }

    public static String getCompany(String token) {
        return validateToken(token).get("company", String.class);
    }

    public static Long getUserId(String token) {
        return validateToken(token).get("userId", Long.class);
    }
}

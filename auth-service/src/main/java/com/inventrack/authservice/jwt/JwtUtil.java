package com.inventrack.authservice.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {


    private final String SECRET = "ThisIsASecretKeyForJwtSigningPurpose1234"; // Should be at least 32 characters for HS256

    private final long EXPIRATION = 1000 * 60 * 60 * 10;

    private SecretKey getSigningKey() {
        if (SECRET == null || SECRET.length() < 32) {
            throw new RuntimeException("JWT SECRET env variable missing or too short.");
        }
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    public String generateToken(String subject, Map<String, Object> claims) {
        SecretKey key = getSigningKey();

        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(key)  // ← no algorithm needed; JJWT detects HS256 automatically
                .compact();
    }

    public Claims validateToken(String token) {
        SecretKey key = getSigningKey();

        return Jwts.parser()
                .verifyWith(key)  // ← correct for JJWT 0.12
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}

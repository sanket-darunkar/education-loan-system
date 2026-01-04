package com.els.educationloansystem.jwt;

import java.security.Key;
import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
@Component
public class JwtUtil {

    private final String SECRET = "mysecretkeymysecretkeymysecretkey";

    // Generate token (subject = email)
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(System.currentTimeMillis() + 1000 * 60 * 10)
                )
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Extract username/email from token
    public String extractUsername(String token) {
        return parse(token).getBody().getSubject();
    }

    // ✅ CORRECT validation (THIS IS THE FIX)
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername())
                && !parse(token).getBody().getExpiration().before(new Date());
    }

    private Jws<Claims> parse(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token);
    }

    private Key getKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }
}



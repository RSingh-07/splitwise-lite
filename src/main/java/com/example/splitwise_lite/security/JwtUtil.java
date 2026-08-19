package com.example.splitwise_lite.security;


import com.example.splitwise_lite.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    private Key getSigningKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        Key key = Keys.hmacShaKeyFor(keyBytes);
        return key;
    }

    public String generateToken(User user){
        return Jwts.builder()
                .subject(user.getEmail())
                .claim("role", user.getRole().name())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSigningKey())
                .compact();
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith((SecretKey)  getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private String extractEmail(String token){
        Claims claims = extractAllClaims(token);
        String email = claims.getSubject();
        return email;
    }

    private String extractRole(String token){
        Claims claims = extractAllClaims(token);
        String role = claims.get("role", String.class);
        return role;
    }

    public boolean isTokenValid(String token){
        try{
            extractAllClaims(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

}

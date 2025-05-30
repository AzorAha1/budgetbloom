package com.footyverse.app.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
// component means this class is a Spring bean and can be injected into other components
//tells Spring “I want you to manage this class.”
//   - That means it becomes a **bean**, and you can inject it in other places with `@Autowired`.
//   - Think of it as saying “Spring, please create and store this for me so I don’t manually do `new JwtUtil()`.”
public class JwtUtil {
    // this class will handle JWT token creation and validation
    // You can implement methods to generate tokens, validate tokens, and extract user information from tokens.
    // For example:
    // secret key
    // This line generates a secret key used to sign tokens using the HS256 algorithm.
    // what is hs256? hs256 is a hashing algorithm used in JWT (JSON Web Tokens) for signing tokens. It ensures that the token is secure and cannot be tampered with.
    // 	Later, it will use the same key to verify that the token hasn’t been changed.
    // The `Keys.secretKeyFor(SignatureAlgorithm.HS256)` method generates a secure random key suitable for the HS256 algorithm.
    private final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // this field holds the expiration time for the JWT token
    // expiration time is set to 24 hours (in milliseconds)
    // it is calculate as 24 hours * 60 minutes * 60 seconds * 1000 milliseconds
    private long expirationTime = 8400000; // 24 hours in milliseconds
    // generate token
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createtoken(claims, userDetails.getUsername());
    }

    // create token
    public String createtoken(Map<String, Object> claims, String subject) {
        // this method creates a JWT token with the given claims and subject
        return Jwts.builder()
            .setClaims(claims) // claims are additional information you want to include in the token
            .setSubject(subject) // this is the subject of the token, usually the username or user ID
            .signWith(secretKey) // this line signs the token with the secret key
            .setIssuedAt(new Date(System.currentTimeMillis())) // sets the issued date to the current time
            .setExpiration(new Date(System.currentTimeMillis() + expirationTime)) // sets the expiration date to the current time plus the expiration time
            .compact(); // this line compacts the token into a string format
    }
}

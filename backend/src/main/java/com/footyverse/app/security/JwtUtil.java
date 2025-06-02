package com.footyverse.app.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
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
    // private final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    @Value("${jwt.secret}")
    private String secret;
    // this field holds the expiration time for the JWT token
    // expiration time is set to 24 hours (in milliseconds)
    // it is calculate as 24 hours * 60 minutes * 60 seconds * 1000 milliseconds
    private static final long expirationTime = 86400000; // 24 hours in milliseconds
    // generate token
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createtoken(claims, userDetails.getUsername());
    }

    // create token
    private String createtoken(Map<String, Object> claims, String subject) {
        // this method creates a JWT token with the given claims and subject
        return Jwts.builder()
            .setClaims(claims) // claims are additional information you want to include in the token
            .setSubject(subject) // this is the subject of the token, usually the username or user ID
            .signWith(getSigningkey()) // this line signs the token with the secret key
            .setIssuedAt(new Date(System.currentTimeMillis())) // sets the issued date to the current time
            .setExpiration(new Date(System.currentTimeMillis() + expirationTime)) // sets the expiration date to the current time plus the expiration time
            .compact(); // this line compacts the token into a string format
    }
    // validate token
    public boolean validateToken(String token, UserDetails userDetails) {
        // this method validates the jwt token 
        String username = extractUsername(token);
        // it checks if the token is valid by comparing the username in the token with the username in the userDetails object
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    // extract Claim
    public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        // this method extracts a specific claim from the token using a function
        // it uses the Jwts.parser() method to parse the token and extract the claims
        Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
        
    }
    // extract all claims
    private Claims extractAllClaims(String token) {
        // this method extracts all claims from the token
        // it uses the Jwts.parser() method to parse the token and extract the claims
        //  it returns a Claims object that contains all the claims in the token
        // it uses the secret key to verify the signature of the token
        // if the token is invalid or expired, it will throw an exception
        return Jwts.parserBuilder() // creates a parser builder for parsing JWT tokens
            .setSigningKey(getSigningkey()) // sets the secret key to verify the signature
            .build() // builds the parser, which is ready to parse tokens
            .parseClaimsJws(token) // parses the token and returns a Jws object, this object contains the claims and the signature
            .getBody(); // gets the body of the Jws object, which contains the claims, this gives a Claims object that contains all the claims in the token
    }

    // this is if you didnt have extractclaim which can return anytype that T is
    // private String extractUsername(String token) {
    //     Claims claims = extractAllClaims(token);
    //     return claims.getSubject(); // extracts the subject from the claims, which is usually the username or user ID
    // }

    private String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // extract expiration date
    private Date extractExprDate(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // check if token is expired
    private boolean isTokenExpired(String token) {
        // this method checks if the token is expired by comparing the current time with the expiration date
        return extractExprDate(token).before(new Date(System.currentTimeMillis()));
    }
    //get signing key
    private SecretKey getSigningkey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

}

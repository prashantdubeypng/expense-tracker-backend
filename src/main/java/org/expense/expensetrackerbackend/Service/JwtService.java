package org.expense.expensetrackerbackend.Service;
/*
 * @author prashant
 * This Java class provides JWT (JSON Web Token) utility functions for token parsing and
 * claims extraction.
 * @services
 * extract date
 * validation
 * generator
 * extractusername
 * istokenexpried
 * Refreshment
 * */

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

public class JwtService {
    /*Secret key for JWT signature generation and verification.
    * @Getter annotation is used to expose the secret key to the application.*/
    @Getter
    private  static final String SECRET = "secret@12@432@7654@991/33@2620***/@@@@@PrashantDEvfdfgfdgjfgdgfseygfyergydfgfyrgydfgfhdgfhdgfgeyger64367563743673476546534fgdhvhdbvhdbvdhvdbvdgvhdfgfg6346473328";
/**
 * Extract the username from the JWT token, and it will be a central place for extracting claims
 * from the token and match the db.
 * @Claims::getSubject is used to retrieve the username from the JWT token.*/
    public String extractusername(String token){
        return extractClaims(token , Claims::getSubject);
    }
    /**
     * Extract the expiration date from the JWT token*/
    public Date extractExpiration(String token){
        return extractClaims(token , Claims::getExpiration);
    }
    /**
     * Checks if the JWT token has expired*/
    public Boolean istokenexpried(String token){
        return extractExpiration(token).before(new Date());
    }
    /**
     * Validates if the JWT token is valid (not expired and matches the username)*/
    public Boolean validatetoken(String token , UserDetails user){
        final String username = extractusername(token);
        return (username.equals(user.getUsername()) && !istokenexpried(token));
    }
    /**
     * Generates a signed JWT token containing the user's username
     * */
    private String generateToken(Map<String , Object> claims , String Username){
        return Jwts.builder().
                setClaims(claims).
                setSubject(Username).
                setIssuedAt(new Date(System.currentTimeMillis())).
                setExpiration(new Date(System.currentTimeMillis()+1000*60*1))
                .signWith(getsignkey(), SignatureAlgorithm.HS256).compact();
    }
    /**
     * A generic method for extracting any claim from the token
     * Takes a function (ClaimsResolver) that specifies which claim to extract
     * Returns the extracted claim of type T
     * This provides flexibility to extract different claims (subject, expiration, custom claims, etc.)*/
    public <T>T extractClaims(String token , Function<Claims , T>ClaimsResolver){
        final Claims claim = extractAllClaims(token);
        return ClaimsResolver.apply(claim);
    }

/**
 * Parses the JWT token and extracts all claims
 * Uses the JJWT library to:
 * Create a parser
 * Set the signing key for verification
 * Parse the signed JWT (JWS)
 * Return the claims body*/
    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .setSigningKey(getsignkey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    /**
     * Converts the BASE64-encoded secret string into a cryptographic Key object
     * Creates an HMAC-SHA key suitable for JWT signing/verification
     * This key is used to verify token signatures*/
    private Key getsignkey(){
        byte[] key = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(key);
    }
}

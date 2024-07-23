package com.pmt.backend.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtProvider {

    // Generate a secure key for HMAC-SHA256
    private static final SecretKey key = Keys.hmacShaKeyFor(Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256).getEncoded());

    public static String generateToken(Authentication auth){

        String jwt = Jwts.builder()
                .setIssuer("Codewithpmt").setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+3600000))
                .claim("email", auth.getName())
                .signWith(key)
                .compact();

        return jwt;
    }

    public static String getEmailFromJwtToken(String jwt){
        //Bearer token: remove "Bearer "
        jwt = jwt.substring(7);

        Claims claims = Jwts.parser().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
        String email = String.valueOf(claims.get("email"));
        return email;
    }
}

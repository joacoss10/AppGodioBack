package com.example.recetarium.demo.Utiles;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private final String claveSecreta="JedsR0sSeCod@wnsaAJedklsGodkaIO123";
    private final long expiration = 1000L * 60 * 60 * 24 * 7;

    private Key getSingInKey(){
        return Keys.hmacShaKeyFor(claveSecreta.getBytes());
    }
    public String generarToken(String alias){
        return Jwts.builder()
                .setSubject(alias)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+expiration))
                .signWith(getSingInKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    public String getUserName(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSingInKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    public boolean validarToken(String token){
        try {
            Jwts.parserBuilder().setSigningKey(getSingInKey()).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

}


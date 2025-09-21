package com.example.aula.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil
{

    private final String SECRET = "meuSegredoMuitoSeguroBatatinhaaaaaaaaaaaaas"; // use algo mais seguro e longo
    private final long EXPIRATION = 1000 * 60 * 60 * 10; // 10 horas

    // Converte a string em uma chave segura
    private Key getSigningKey()
    {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    public String gerarToken(String username)
    {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String getUsername(String token)
    {
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validarToken(String token)
    {
        try
        {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e)
        {
            return false;
        }
    }
}

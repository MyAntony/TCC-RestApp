package com.example.restapp.security;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.example.restapp.entity.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil
{

    private final String SECRET = "QWERTHYUIOPASDFGHJKLZXCVBNM1234567890qwertyuiopasdfghjklzxcvbnm!@#$%ˆ&*()";
    private final long EXPIRATION = 1000 * 60 * 60 * 10; // 10 horas

    // Converte SECRET em chave segura
    private Key getSigningKey()
    {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    // Agora está correto
    public Claims getClaims(String token)
    {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String gerarToken(Usuario usuario)
    {
        return Jwts.builder()
                .setSubject(usuario.getEmail())
                .claim("role", usuario.getCargo().name())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String getUsername(String token)
    {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
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

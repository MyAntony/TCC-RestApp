package com.example.restapp.security;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController
{

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authManager, JwtUtil jwtUtil)
    {
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> dados)
    {
        String email = dados.get("email");
        String senha = dados.get("senha");

        System.out.println("Login solicitado para: " + email);
        System.out.println("Senha enviada: " + senha);

        Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(email, senha));

        UsuarioPrincipal user = (UsuarioPrincipal) auth.getPrincipal();
        String token = jwtUtil.gerarToken(user.getUsername());

        return ResponseEntity.ok(Map.of("token", token));

    }

}

package com.example.restapp.security;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        String token = jwtUtil.gerarToken(user.getUsuario());

        return ResponseEntity.ok(Map.of("token", token));

    }

}

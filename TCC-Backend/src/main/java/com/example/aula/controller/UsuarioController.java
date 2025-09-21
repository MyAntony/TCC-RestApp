package com.example.aula.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.aula.service.UsuarioService;

import jakarta.validation.Valid;

import com.example.aula.dto.UsuarioDTO;
import com.example.aula.dto.UsuarioResponseDTO;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController
{
    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService)
    {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarUsuarios()
    {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @PostMapping
    public ResponseEntity<Map<Object, String>> salvarUsuario(@Valid @RequestBody UsuarioDTO dto)
    {
        usuarioService.salvarUsuario(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Usuário cadastrado com sucesso!"));
    }
}

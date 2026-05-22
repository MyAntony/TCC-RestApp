package com.example.restapp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.restapp.dto.usuario.UsuarioRequestDTO;
import com.example.restapp.dto.usuario.UsuarioResponseDTO;
import com.example.restapp.service.UsuarioService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController
{
    @Autowired
    private UsuarioService usuarioService;

    // Post
    @PostMapping
    public ResponseEntity<Map<Object, String>> salvarUsuario(@Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO)
    {
        usuarioService.salvarUsuario(usuarioRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Usuário cadastrado com sucesso!"));
    }

    // Read
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarUsuarios()
    {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    // Put
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> atualizarUsuario(@PathVariable Long id, @RequestBody UsuarioRequestDTO usuarioRequestDTO)
    {
        usuarioService.atualizarUsuario(id, usuarioRequestDTO);

        return ResponseEntity.status(HttpStatus.OK).body(Map.of("mensagem", "Usuário atualizado com sucesso"));
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> excluirUsuario(@PathVariable Long id)
    {
        usuarioService.excluirUsuario(id);

        return ResponseEntity.status(HttpStatus.OK).body(Map.of("mensagem", "O usuário foi deletado com sucesso"));
    }

}

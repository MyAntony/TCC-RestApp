package com.example.restapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.restapp.dto.usuario.UsuarioRequestDTO;
import com.example.restapp.dto.usuario.UsuarioResponseDTO;
import com.example.restapp.model.Usuario;
import com.example.restapp.repository.UsuarioRepository;

import jakarta.validation.Valid;

@Service
@Validated
public class UsuarioService
{
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public Usuario salvarUsuario(@Valid UsuarioRequestDTO usuarioRequestDTO)
    {
        usuarioRepository.findByEmail(usuarioRequestDTO.getEmail())
            .ifPresent(u -> { throw new IllegalArgumentException("E-mail já cadastrado!"); });

        Usuario usuario = new Usuario();
        usuario.setNome(usuarioRequestDTO.getNome());
        usuario.setEmail(usuarioRequestDTO.getEmail());
        usuario.setCargo(usuarioRequestDTO.getCargo());
        usuario.setSenha(bCryptPasswordEncoder.encode(usuarioRequestDTO.getSenha())); // Criptografando a senha.

        return usuarioRepository.save(usuario);
    }

    // Read
    public List<UsuarioResponseDTO> listarUsuarios()
    {
        return usuarioRepository.findAll()
            .stream()
            .map(this::toResponseDTO)
            .toList();
    }

    // Put
    public UsuarioResponseDTO atualizarUsuario(Long id, @Valid UsuarioRequestDTO usuarioRequestDTO)
    {
        Usuario atualizarUsuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        atualizarUsuario.setNome(usuarioRequestDTO.getNome());
        atualizarUsuario.setEmail(usuarioRequestDTO.getEmail());
        atualizarUsuario.setCargo(usuarioRequestDTO.getCargo());
        atualizarUsuario.setSenha(usuarioRequestDTO.getSenha());

        return toResponseDTO(usuarioRepository.save(atualizarUsuario));
    }

    // Delete
    public void excluirUsuario(Long id)
    {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        usuarioRepository.deleteById(usuario.getId());
    }

    private UsuarioResponseDTO toResponseDTO(Usuario usuario)
    {
        return new UsuarioResponseDTO(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getCargo());
    }

}
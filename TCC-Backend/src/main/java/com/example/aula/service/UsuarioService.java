package com.example.aula.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.aula.dto.UsuarioDTO;
import com.example.aula.dto.UsuarioResponseDTO;
import com.example.aula.model.Usuario;
import com.example.aula.repository.UsuarioRepository;

@Service
public class UsuarioService
{
   private UsuarioRepository usuarioRepository;
   private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, BCryptPasswordEncoder bCryptPasswordEncoder)
    {
        this.usuarioRepository = usuarioRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Usuario salvarUsuario(UsuarioDTO dto)
    {
        usuarioRepository.findByEmail(dto.getEmail())
            .ifPresent(u -> { throw new IllegalArgumentException("E-mail já cadastrado!"); });

        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setCargo(dto.getCargo());
        usuario.setSenha(bCryptPasswordEncoder.encode(dto.getSenha())); // Criptografando a senha.

        return usuarioRepository.save(usuario);
    }

public List<UsuarioResponseDTO> listarUsuarios()
{
    return usuarioRepository.findAll()
        .stream()
        .map(this::toDTO)
        .toList();
}

private UsuarioResponseDTO toDTO(Usuario usuario)
{
    return new UsuarioResponseDTO(usuario.getNome(), usuario.getEmail(), usuario.getCargo());
}

}
package com.example.aula.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.aula.repository.UsuarioRepository;

@Service
public class UsuarioDetailsService implements UserDetailsService
{

    private final UsuarioRepository usuarioRepository;

    public UsuarioDetailsService(UsuarioRepository usuarioRepository)
    {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {
        return usuarioRepository.findByEmail(email).map(UsuarioPrincipal::new).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + email));
    }
}

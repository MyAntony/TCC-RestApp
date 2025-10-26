package com.example.restapp.init;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.restapp.model.Cargo;
import com.example.restapp.model.Usuario;
import com.example.restapp.repository.UsuarioRepository;

import jakarta.annotation.PostConstruct;

@Component
public class DataInitializer
{

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public DataInitializer(UsuarioRepository usuarioRepository, BCryptPasswordEncoder passwordEncoder)
    {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init()
    {
        // Verifica se já existe algum usuário no banco
        if (usuarioRepository.findAll().isEmpty())
        {
            Usuario admin = new Usuario();
            admin.setNome("Administrador");
            admin.setEmail("admin@restaurante.com");
            admin.setSenha(passwordEncoder.encode("123456")); // senha inicial
            admin.setCargo(Cargo.ADMINISTRADOR);

            usuarioRepository.save(admin);
            System.out.println("Administrador inicial criado com sucesso!");
        }
    }
}

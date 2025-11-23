package com.example.restapp.init;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.restapp.model.Cargo;
import com.example.restapp.model.Usuario;
import com.example.restapp.model.principal.Mesa;
import com.example.restapp.repository.MesaRepository;
import com.example.restapp.repository.UsuarioRepository;

import jakarta.annotation.PostConstruct;

@Component
public class DataInitializer
{

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    private final MesaRepository mesaRepository;

    public DataInitializer(UsuarioRepository usuarioRepository, BCryptPasswordEncoder passwordEncoder, MesaRepository mesaRepository)
    {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.mesaRepository = mesaRepository;
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
            admin.setSenha(passwordEncoder.encode("123456")); // Senha inicial
            admin.setCargo(Cargo.ADMINISTRADOR);

            usuarioRepository.save(admin);
            System.out.println("Administrador inicial criado com sucesso!");
        }
        
        // Verifica se já existem mesas no banco, no futuro devemos colocar uma configuração para o usuário definir a quantidade de mesas
        if (mesaRepository.count() == 0)
        {
            for (long i = 1; i <= 100; i++) // Tentei usar o Long, mas não funciona, e o int dá conflito por não ser o mesmo que Long
            {
                Mesa mesa = new Mesa();
                mesa.setId(i); // número da mesa = id
                mesaRepository.save(mesa);
            }
            System.out.println("Mesas criadas automaticamente!");
        }
    }
}

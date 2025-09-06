package com.example.aula.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tab_usuario")
@Data
public class Usuario
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

}

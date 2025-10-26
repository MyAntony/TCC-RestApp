package com.example.restapp.dto;

import com.example.restapp.model.Cargo;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter // Gera os getters
@Setter // Gera os setters
@AllArgsConstructor // Gera o construtor com todos os argumentos
@NoArgsConstructor // Gera o construtor sem argumentos
public class UsuarioDTO
{
    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @Email(message = "O email é obrigatório")
    private String email;

    @NotBlank(message = "A senha é obrigatória")
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Cargo cargo;

    
}

package com.example.aula.dto;

import com.example.aula.model.Cargo;
import lombok.*;

@Getter // Gera os getters
@Setter // Gera os setters
@AllArgsConstructor // Gera o construtor com todos os argumentos
@NoArgsConstructor // Gera o construtor sem argumentos
public class UsuarioResponseDTO
{
    private String nome;
    private String email;
    private Cargo cargo;
    
}

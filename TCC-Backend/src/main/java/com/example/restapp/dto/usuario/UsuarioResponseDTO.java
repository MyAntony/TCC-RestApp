package com.example.restapp.dto.usuario;

import com.example.restapp.model.Cargo;

import lombok.*;

import java.util.UUID;

@Getter // Gera os getters
@Setter // Gera os setters
@AllArgsConstructor // Gera o construtor com todos os argumentos
@NoArgsConstructor // Gera o construtor sem argumentos
public class UsuarioResponseDTO
{
    private UUID idAuxiliar;
    private String nome;
    private String email;
    private Cargo cargo;
}

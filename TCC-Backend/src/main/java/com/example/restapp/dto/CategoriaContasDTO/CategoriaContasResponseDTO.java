package com.example.restapp.dto.CategoriaContasDTO;

import lombok.*;

@Getter // Gera os getters
@Setter // Gera os setters
@AllArgsConstructor // Gera o construtor com todos os argumentos
@NoArgsConstructor // Gera o construtor sem argumentos
public class CategoriaContasResponseDTO
{
    private Long id;
    private String nomeCategoria;
    private String descricaoCategoria;
}

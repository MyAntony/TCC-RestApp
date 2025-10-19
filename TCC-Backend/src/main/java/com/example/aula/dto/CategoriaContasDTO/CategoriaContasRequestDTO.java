package com.example.aula.dto.CategoriaContasDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter // Gera os getters
@Setter // Gera os setters
@AllArgsConstructor // Gera o construtor com todos os argumentos
@NoArgsConstructor // Gera o construtor sem argumentos
public class CategoriaContasRequestDTO
{
    @NotBlank(message = "O nome da categoria é obrigatório.")
    private String nomeCategoria;

    private String descricaoCategoria;
}

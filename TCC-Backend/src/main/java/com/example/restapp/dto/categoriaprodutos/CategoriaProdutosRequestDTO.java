package com.example.restapp.dto.categoriaprodutos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter // Gera os getters
@Setter // Gera os setters
@AllArgsConstructor // Gera o construtor com todos os argumentos
@NoArgsConstructor // Gera o construtor sem argumentos
public class CategoriaProdutosRequestDTO
{
    @NotBlank(message = "O nome da categoria é obrigatório")
    private String nomeCategoriaProdutos;
    private String descricaoCategoriaProdutos;
}

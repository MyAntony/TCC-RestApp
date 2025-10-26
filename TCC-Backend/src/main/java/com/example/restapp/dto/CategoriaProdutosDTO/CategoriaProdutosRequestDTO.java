package com.example.restapp.dto.CategoriaProdutosDTO;

import lombok.*;

@Getter // Gera os getters
@Setter // Gera os setters
@AllArgsConstructor // Gera o construtor com todos os argumentos
@NoArgsConstructor // Gera o construtor sem argumentos
public class CategoriaProdutosRequestDTO
{
    private String nomeCategoriaProdutos;
    private String descricaoCategoriaProdutos;
}

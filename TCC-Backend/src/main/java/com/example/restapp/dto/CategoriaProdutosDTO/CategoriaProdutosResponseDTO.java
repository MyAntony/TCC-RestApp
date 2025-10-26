package com.example.restapp.dto.CategoriaProdutosDTO;

import java.time.*;
import lombok.*;

@Getter // Gera os getters
@AllArgsConstructor // Gera o construtor com todos os argumentos
@NoArgsConstructor // Gera o construtor sem argumentos
public class CategoriaProdutosResponseDTO
{
    private Long id;
    private String nomeCategoriaProdutos;
    private String descricaoCategoriaProdutos;
    private LocalDate dataCriacao;
    
}

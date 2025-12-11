package com.example.restapp.dto.produto;

import java.math.BigDecimal;

import lombok.*;

@Getter // Gera os getters
@Setter // Gera os setters
@AllArgsConstructor // Gera o construtor com todos os argumentos
@NoArgsConstructor // Gera o construtor sem argumentos
public class ProdutoRequestDTO
{
    private String nomeProduto;
    private Long idCategoriaProdutos;
    private BigDecimal precoCusto;
    private BigDecimal precoVenda;
    private String descricao;
    private String imagem;
}

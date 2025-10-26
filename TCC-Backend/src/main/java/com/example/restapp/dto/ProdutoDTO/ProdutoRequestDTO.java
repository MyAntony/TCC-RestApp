package com.example.restapp.dto.ProdutoDTO;

import lombok.*;

@Getter // Gera os getters
@Setter // Gera os setters
@AllArgsConstructor // Gera o construtor com todos os argumentos
@NoArgsConstructor // Gera o construtor sem argumentos
public class ProdutoRequestDTO
{
    private String nomeProduto;
    private Long idCategoriaProdutos;
    private Double precoCusto;
    private Double precoVenda;
    private String descricao;
    private byte[] imagem;
}

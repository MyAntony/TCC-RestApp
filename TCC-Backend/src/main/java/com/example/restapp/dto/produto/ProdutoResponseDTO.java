package com.example.restapp.dto.produto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoResponseDTO
{
    private Long id;
    private String nomeProduto;
    private String nomeCategoria;
    private BigDecimal precoCusto;
    private BigDecimal precoVenda;
    private String descricao;
    private String imagem;

}

package com.example.restapp.dto.ProdutoDTO;

import java.math.BigDecimal;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoResponseDTO
{
    private Long id;
    private String nomeProduto;
    private String nomeCateogria;
    private BigDecimal precoCusto;
    private BigDecimal precoVenda;
    private String descricao;
    private byte[] imagem;

}

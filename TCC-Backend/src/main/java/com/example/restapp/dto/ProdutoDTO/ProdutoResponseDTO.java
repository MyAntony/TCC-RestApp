package com.example.restapp.dto.ProdutoDTO;

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
    private Double precoCusto;
    private Double precoVenda;
    private String descricao;
    private byte[] imagem;

}

package com.example.restapp.dto.PedidoDTO;

import lombok.*;

@Getter // Gera os getters
@Setter // Gera os setters
@AllArgsConstructor // Gera o construtor com todos os argumentos
@NoArgsConstructor // Gera o construtor sem argumentos
public class PedidoRequestDTO
{
    private Integer mesa;
    private Long idProduto;
    private String descricaoPedido;
    private Integer quantidadeProduto;
}

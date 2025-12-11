package com.example.restapp.dto.pedido;

import lombok.*;

@Getter // Gera os getters
@Setter // Gera os setters
@AllArgsConstructor // Gera o construtor com todos os argumentos
@NoArgsConstructor // Gera o construtor sem argumentos
public class PedidoRequestDTO
{
    // private Long idMesa;
    private Long idProduto;
    private String descricaoPedido;
    private Integer quantidadeProduto;
}

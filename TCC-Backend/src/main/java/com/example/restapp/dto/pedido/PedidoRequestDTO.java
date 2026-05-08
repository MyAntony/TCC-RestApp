package com.example.restapp.dto.pedido;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter // Gera os getters
@Setter // Gera os setters
@AllArgsConstructor // Gera o construtor com todos os argumentos
@NoArgsConstructor // Gera o construtor sem argumentos
public class PedidoRequestDTO
{
    @NotNull(message = "O ID do produto é obrigatório")
    private Long idProduto;
    private String descricaoPedido;
    private Integer quantidadeProduto;
}

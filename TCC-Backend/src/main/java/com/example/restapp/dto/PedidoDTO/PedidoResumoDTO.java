package com.example.restapp.dto.PedidoDTO;

import java.math.BigDecimal;

import lombok.*;

@Getter // Gera os getters
@Setter // Gera os setters
@NoArgsConstructor // Gera o construtor sem argumentos
@AllArgsConstructor // Gera o construtor com todos os argumentos
public class PedidoResumoDTO
{
    private String nomeProduto;
    private Integer quantidade;
    private BigDecimal valorUnitario;
    private BigDecimal valorTotal;
}

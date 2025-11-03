package com.example.restapp.dto.PedidoDTO;

import java.time.*;
import lombok.*;

@Getter // Gera os getters
@Setter // Gera os setters
@AllArgsConstructor // Gera o construtor com todos os argumentos
@NoArgsConstructor // Gera o construtor sem argumentos
public class PedidoResponseDTO
{
    private Long id;
    private String nomeUsuario;
    private Integer mesa;
    private String nomeProduto;
    private String descricaoPedido;
    private Integer quantidadeProduto;
    private Double valorUnitario;
    private Double valorTotal;
    private LocalDateTime horarioLancamento;
}

package com.example.restapp.dto.PagamentoDTO;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class PagamentoResponseDTO
{

    private Long id;
    private String usuario;
    private Long numeroMesa;
    private BigDecimal valorPagamento;
    private String nomeMetodoPagamento;
    private LocalDateTime horarioLancamento;
}

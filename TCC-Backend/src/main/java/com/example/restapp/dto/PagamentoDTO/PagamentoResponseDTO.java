package com.example.restapp.model.dto.pagamento;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PagamentoResponseDTO {

    private Long id;
    private Double valorPagamento;

    private Long usuarioId;
    private String usuarioNome;

    private Long mesaId;
    private Integer numeroMesa;

    private Long metodoPagamentoId;
    private String nomeMetodoPagamento;

    private String criadoPor;
    private LocalDateTime horarioLancamento;
}

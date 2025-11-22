package com.example.restapp.model.dto.pagamento;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagamentoRequestDTO {

    private Double valorPagamento;
    private Long usuarioId;
    private Long mesaId;
    private Long metodoPagamentoId;

}

package com.example.restapp.dto.PagamentoDTO;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagamentoRequestDTO
{
    private BigDecimal valorPagamento;
    private Long metodoPagamentoId;

}

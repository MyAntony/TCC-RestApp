package com.example.restapp.dto.pagamento;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagamentoRequestDTO
{
    @NotNull(message = "O valor do pagamento é obrigatório")
    private BigDecimal valorPagamento;

    @NotNull(message = "O método de pagamento é obrigatório")
    private Long metodoPagamentoId;

}

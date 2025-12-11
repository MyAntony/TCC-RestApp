package com.example.restapp.dto.pagamento;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

@Getter
@Setter
public class PagamentoResponseDTO
{

    private Long id;
    private String usuario;
    private Long numeroMesa;
    private BigDecimal valorPagamento;
    private String nomeMetodoPagamento;
    @JsonFormat(pattern = "dd-MM-yyyy 'T' HH:mm:ss")
    private LocalDateTime horarioLancamento;
}

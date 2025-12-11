package com.example.restapp.dto.contaspagar;

import java.math.BigDecimal;
import java.time.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ContasPagarResponseDTO
{
    private Long id;
    private String nomeCategoria;
    private String nomeFornecedor;
    private String descricao;
    private LocalDate dataVencimento;
    private BigDecimal valor;
    private LocalDate dataPagamento;
    private String statusPagamento;
}

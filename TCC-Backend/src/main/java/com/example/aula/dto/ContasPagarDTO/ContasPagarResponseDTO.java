package com.example.aula.dto.ContasPagarDTO;

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
    private Double valor;
    private LocalDate dataPagamento;
    private String statusPagamento;
}

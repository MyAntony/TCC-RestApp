package com.example.restapp.dto.contaspagar;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter // Gera os getters
@Setter // Gera os setters
@AllArgsConstructor // Gera o construtor com todos os argumentos
@NoArgsConstructor // Gera o construtor sem argumentos
public class ContasPagarRequestDTO
{

    private Long categoriaId;
    private Long fornecedorId;
    private String descricao;
    private LocalDate dataVencimento;
    private BigDecimal valor;
    private LocalDate dataPagamento;
    private String statusPagamento;
}

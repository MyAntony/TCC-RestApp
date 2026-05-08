package com.example.restapp.dto.contaspagar;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotNull(message = "O ID da categoria é obrigatório")
    private Long categoriaId;

    @NotNull(message = "O ID do fornecedor é obrigatório")
    private Long fornecedorId;

    @Size(max = 255, message = "A descrição deve ter no máximo 255 caracteres.")
    private String descricao;

    @NotNull(message = "A data de vencimento é obrigatória")
    private LocalDate dataVencimento;

    @NotNull(message = "O valor é obrigatório")
    @DecimalMin(value = "0.0", inclusive = false, message = "O valor deve ser maior que zero.")
    private BigDecimal valor;
    private LocalDate dataPagamento;
    private String statusPagamento;
}

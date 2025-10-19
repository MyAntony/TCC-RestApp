package com.example.aula.dto.ContasPagarDTO;

import java.time.LocalDate;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter // Gera os getters
@Setter // Gera os setters
@AllArgsConstructor // Gera o construtor com todos os argumentos
@NoArgsConstructor // Gera o construtor sem argumentos
public class ContasPagarRequestDTO
{

    @NotNull(message = "A categoria é obrigatória.")
    private Long categoriaId;

    @NotNull(message = "O fornecedor é obrigatório.")
    private Long fornecedorId;

    @Size(max = 255, message = "A descrição deve ter no máximo 255 caracteres.")
    private String descricao;

    @NotNull(message = "A data de vencimento é obrigatória.")
    private LocalDate dataVencimento;

    @NotNull(message = "O valor é obrigatório.")
    @DecimalMin(value = "0.0", inclusive = false, message = "O valor deve ser maior que zero.")
    private Double valor;

    private LocalDate dataPagamento;

    @NotNull(message = "O status do pagamento é obrigatório.")
    private String statusPagamento;
}

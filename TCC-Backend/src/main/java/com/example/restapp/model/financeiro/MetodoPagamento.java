package com.example.restapp.model.financeiro;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter // Gera os getters
@Setter // Gera os setters
@AllArgsConstructor // Gera o construtor com todos os argumentos
@NoArgsConstructor // Gera o construtor sem argumentos

public class MetodoPagamento // Necessário criar RequestDTO e ResponseDTO para esta entidade
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @NotBlank(message = "O nome do método de pagamento é obrigatório.")
    @JoinColumn(nullable = false)
    private String nomeMetodoPagamento;

    @Enumerated(EnumType.STRING)
    @JoinColumn(nullable = false)
    private TipoMetodoPagamento tipoMetodoPagamento;
    
    @Enumerated(EnumType.STRING)
    private BandeiraCartao bandeira; // Pode ser null para métodos não-cartão

    private BigDecimal taxa; // Pode ser null se não aplicável
    
}

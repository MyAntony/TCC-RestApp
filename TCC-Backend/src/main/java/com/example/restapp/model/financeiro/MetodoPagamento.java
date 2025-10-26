package com.example.restapp.model.financeiro;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Getter // Gera os getters
@Setter // Gera os setters
@AllArgsConstructor // Gera o construtor com todos os argumentos
@NoArgsConstructor // Gera o construtor sem argumentos

public class MetodoPagamento
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do método de pagamento é obrigatório.")
    private String nomeMetodoPagamento;

    @Enumerated(EnumType.STRING)
    private TipoMetodoPagamento tipoMetodoPagamento;
    
    @Enumerated(EnumType.STRING)
    private BandeiraCartao bandeira; // Pode ser null para métodos não-cartão

    private double taxa; // Pode ser null se não aplicável
    
}

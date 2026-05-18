package com.example.restapp.dto.mesasessao;

import java.math.BigDecimal;

import com.example.restapp.model.enums.StatusMesa;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MesaSessaoRequestDTO
{
    private Long idAtendenteResponsavel;

    @NotNull(message = "O número da mesa é obrigatório")
    private Long numeroMesa;
    
    private Integer quantidadePessoas;
    private Long idCliente;
    private StatusMesa status;
    // private BigDecimal taxaServico;
    
}

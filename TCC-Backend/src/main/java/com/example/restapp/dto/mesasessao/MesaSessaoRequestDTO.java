package com.example.restapp.dto.mesasessao;

import java.math.BigDecimal;

import com.example.restapp.model.enums.StatusMesa;

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
    private Long numeroMesa;
    private Integer quantidadePessoas;
    private Long idCliente;
    private StatusMesa status;
    private BigDecimal taxaServico;
    
}

package com.example.restapp.dto.MesaSessaoDTO;

import java.time.LocalDateTime;
import java.util.List;

import com.example.restapp.dto.PedidoDTO.PedidoResumoDTO;
import com.example.restapp.model.enums.StatusMesa;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MesaSessaoResponseDTO
{
    private Long id;
    private String atendenteAbertura;
    private String nomeAtendenteResponsavel;
    private Long numeroMesa;
    private Integer quantidadePessoas;
    private String nomeCliente;
    private StatusMesa status;
    @JsonFormat(pattern = "dd-MM-yyyy 'T' HH:mm:ss")
    private LocalDateTime horarioAbertura;
    @JsonFormat(pattern = "dd-MM-yyyy 'T' HH:mm:ss")
    private LocalDateTime horarioFechamento;
    private List<PedidoResumoDTO> pedidos;
    private Double valorTotalMesa;
    private Double valorTotalMesaServico;

}

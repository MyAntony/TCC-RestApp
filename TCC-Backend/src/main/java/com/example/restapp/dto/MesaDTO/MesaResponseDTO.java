package com.example.restapp.dto.MesaDTO;

import java.time.LocalDateTime;
import java.util.List;

import com.example.restapp.dto.PedidoDTO.PedidoResumoDTO;
import com.example.restapp.model.enums.StatusMesa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MesaResponseDTO
{
    private Long id;
    private String atendenteAbertura;
    private String nomeAtendenteResponsavel;
    private Integer numeroMesa;
    private Integer quantidadePessoas;
    private String nomeCliente;
    private StatusMesa status;
    private LocalDateTime horarioAbertura;
    private LocalDateTime horarioFechamento;
    private List<PedidoResumoDTO> pedidos;
    private Double valorTotalMesa;
    private Double valorTotalMesaServico;

}

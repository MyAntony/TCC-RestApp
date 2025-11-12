package com.example.restapp.dto.MesaDTO;

import com.example.restapp.model.enums.StatusMesa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MesaRequestDTO
{
    private Long idAtendenteResponsavel;
    private Integer numeroMesa;
    private Integer quantidadePessoas;
    private Long idCliente;
    private StatusMesa status;
    
}

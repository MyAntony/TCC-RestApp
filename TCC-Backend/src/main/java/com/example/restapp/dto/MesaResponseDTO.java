package com.example.restapp.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;
import com.example.restapp.model.enums.StatusMesa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MesaResponseDTO {
    private Long id;
    private Integer numeroMesa;
    private Integer capacidade;
    private StatusMesa status;
    private LocalDateTime horarioAbertura;
    private LocalTime horarioFechamento;
    private String garcomResponsavel;

}

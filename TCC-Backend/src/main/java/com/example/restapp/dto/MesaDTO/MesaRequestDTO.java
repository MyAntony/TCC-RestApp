package com.example.restapp.dto.MesaDTO;

import java.time.LocalDateTime;
import java.time.LocalTime;

import com.example.restapp.model.enums.StatusMesa;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MesaRequestDTO {
    @NotNull(message = "O numero da mesa é obrigatório.")
    private Integer numeroMesa;
    
    @NotNull(message = "A capacidade da mesa é obrigatória.")
    private Integer capacidade;

    @NotNull(message = "O status da mesa é obrigatório.")
    private StatusMesa status;
    
    @NotNull(message = "O horário de abertura é obrigatório.")
    private LocalDateTime horarioAbertura;

    @NotNull(message = "O horário de fechamento é obrigatório.")
    private LocalTime horarioFechamento;

    @NotBlank (message = "O nome do garçom responsável é obrigatorio.")
    private String garcomResponsavel;
    
}

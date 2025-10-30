package com.example.restapp.model;

import java.time.LocalDateTime;
import java.time.LocalTime;

import com.example.restapp.model.enums.StatusMesa;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O número da mesa é obrigatório.")
    @Column(unique = true) 
    private Integer numeroMesa;

    @NotNull(message = "A capacidade da mesa é obrigatoria.")
    private Integer capacidade;
    
    @Enumerated(EnumType.STRING)
    @NotNull(message = "O status da mesa é obrigatória.")
    private StatusMesa status;

    @NotNull(message = "O horario de abertura é obrigatório.")
    private LocalDateTime horarioAbertura;

    @NotNull(message = "O horário de fechamento é obrigatorio.")
    private LocalTime horarioFechamento;

    @NotBlank(message = "O nome do garçom responsável é obrigatório.")
    private String garcomResponsavel;
    
}


package com.example.restapp.dto.cliente;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteResponseDTO
{
    private UUID idAuxiliar;
    private String nome;
}

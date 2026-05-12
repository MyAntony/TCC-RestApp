package com.example.restapp.dto.cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.example.restapp.model.Endereco;

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
    private List<Endereco> enderecos = new ArrayList<>();
}

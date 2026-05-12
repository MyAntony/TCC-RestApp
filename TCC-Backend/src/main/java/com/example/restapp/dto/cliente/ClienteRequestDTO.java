package com.example.restapp.dto.cliente;

import java.util.ArrayList;
import java.util.List;

import com.example.restapp.model.Endereco;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRequestDTO
{
    @NotBlank(message = "O nome é obrigatório.")
    private String nome;

    @Size(min = 11, max = 11, message = "O CPF deve conter pelo menos 11 caracteres.")
    private String cpf;

    private String telefone;

    @Email(message = "O email deve ser válido.")
    private String email;

    private List<Endereco> enderecos = new ArrayList<>();
}

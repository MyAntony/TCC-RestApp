package com.example.restapp.dto.cliente;

import com.example.restapp.model.Endereco;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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

    @Min(value = 11, message = "O CPF deve conter pelo menos 11 caracteres.")
    @Max(value = 11, message = "O CPF deve conter no máximo 11 caracteres.")
    private String cpf;

    private String telefone;

    @Email(message = "O email deve ser válido.")
    private String email;

    private Endereco endereco;
}

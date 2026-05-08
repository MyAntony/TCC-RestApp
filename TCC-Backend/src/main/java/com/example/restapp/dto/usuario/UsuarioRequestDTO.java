package com.example.restapp.dto.usuario;

import com.example.restapp.model.Cargo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter // Gera os getters
@Setter // Gera os setters
@AllArgsConstructor // Gera o construtor com todos os argumentos
@NoArgsConstructor // Gera o construtor sem argumentos
public class UsuarioRequestDTO
{
    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @Email(message = "O email é obrigatório")
    private String email;

    @NotBlank(message = "A senha é obrigatória")
    private String senha;

    private Cargo cargo;
}

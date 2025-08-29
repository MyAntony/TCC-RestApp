package com.example.aula.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class CadastroFornecedores
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do fornecedor é obrigatório.")
    private String nome;

    @NotBlank(message = "O CNPJ do fornecedor é obrigatório.")
    private String cnpj;

    @NotBlank(message = "O telefone do fornecedor é obrigatório.")
    private String telefone;

    @NotBlank(message = "O email do fornecedor é obrigatório.")
    @Email(message = "Email inválido.")
    private String email;

    
}

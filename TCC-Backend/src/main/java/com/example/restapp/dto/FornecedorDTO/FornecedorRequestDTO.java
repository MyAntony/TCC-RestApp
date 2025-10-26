package com.example.restapp.dto.FornecedorDTO;

import com.example.restapp.model.Endereco;
import com.example.restapp.model.TipoDocumento;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter // Gera os getters
@Setter // Gera os setters
@AllArgsConstructor // Gera o construtor com todos os argumentos
@NoArgsConstructor // Gera o construtor sem argumentos
public class FornecedorRequestDTO
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nomeFantasia;

    private String razaoSocial;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoDocumento tipoDocumento;

    @Size(max = 11, message = "CPF deve conter 11 dígitos.")
    private String cpf;

    @Size(max = 14, message = "CNPJ deve conter 14 dígitos.")
    private String cnpj;

    private String telefone;

    private String email;

    @Embedded // Indica que o endereço é um campo embutido
    private Endereco endereco;

}

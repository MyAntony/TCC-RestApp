package com.example.restapp.dto.fornecedor;

import com.example.restapp.model.Endereco;
import com.example.restapp.model.TipoDocumento;

import jakarta.persistence.Embedded;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter // Gera os getters
@Setter // Gera os setters
@AllArgsConstructor // Gera o construtor com todos os argumentos
@NoArgsConstructor // Gera o construtor sem argumentos
public class FornecedorRequestDTO
{
    private String nomeFantasia;

    @NotBlank(message = "A Razão Social do fornecedor é obrigatória")
    private String razaoSocial;

    @NotNull(message = "O tipo de documento é obrigatório")
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

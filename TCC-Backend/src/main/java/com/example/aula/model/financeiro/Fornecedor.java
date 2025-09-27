package com.example.aula.model.financeiro;

import com.example.aula.model.Endereco;
import com.example.aula.model.TipoDocumento;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Getter // Gera os getters
@Setter // Gera os setters
@AllArgsConstructor // Gera o construtor com todos os argumentos
@NoArgsConstructor // Gera o construtor sem argumentos

@Table(name = "fornecedor", uniqueConstraints = @UniqueConstraint(columnNames = "cnpj"))
public class Fornecedor
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nomeFantasia;

    private String razaoSocial;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoDocumento tipoDocumento;

    // @Size(min = 11, max = 11, message = "CPF deve conter 11 dígitos.")
    private String cpf;

    @Size(min = 14, max = 14, message = "CNPJ deve conter 14 dígitos.")
    private String cnpj;

    private String telefone;

    private String email;

    @Embedded // Indica que o endereço é um campo embutido
    private Endereco endereco;
    
}

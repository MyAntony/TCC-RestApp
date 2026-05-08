package com.example.restapp.model.financeiro;

import com.example.restapp.model.Endereco;
import com.example.restapp.model.TipoDocumento;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter // Gera os getters
@Setter // Gera os setters
@AllArgsConstructor // Gera o construtor com todos os argumentos
@NoArgsConstructor // Gera o construtor sem argumentos
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "cnpj"))
public class Fornecedor
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nomeFantasia;

    @Column(nullable = false)
    private String razaoSocial;

    @Enumerated(EnumType.STRING)
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

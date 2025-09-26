package com.example.aula.model.cliente;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Getter // Gera os getters
@Setter // Gera os setters
@AllArgsConstructor // Gera o construtor com todos os argumentos
@NoArgsConstructor // Gera o construtor sem argumentos
@Table(name = "cliente", uniqueConstraints = @UniqueConstraint(columnNames = "cpf"))
public class Cliente
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório.")
    private String nome;

    private String cpf;

    private String telefone;

    private String email;

}

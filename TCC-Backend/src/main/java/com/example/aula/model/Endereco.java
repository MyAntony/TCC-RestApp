package com.example.aula.model;

import jakarta.persistence.*;
import lombok.*;

@Getter // Gera os getters
@Setter // Gera os setters
@AllArgsConstructor // Gera o construtor com todos os argumentos
@NoArgsConstructor // Gera o construtor sem argumentos
@Embeddable // Indica que essa classe pode ser embutida em outras entidades
public class Endereco
{
    private String cep;
    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String complemento;
}

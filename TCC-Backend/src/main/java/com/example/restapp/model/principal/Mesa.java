package com.example.restapp.model.principal;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter // Gera os getters
@Setter // Gera os setters
@AllArgsConstructor // Gera o construtor com todos os argumentos
@NoArgsConstructor // Gera o construtor sem argumentos
public class Mesa
{
    @Id
    private Long id;
}

package com.example.restapp.model.produtos;

import java.time.*;
import java.util.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter 
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CategoriaProdutos
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome da categoria do produto é obrigatório.")
    private String nomeCategoriaProdutos;

    private String descricaoCategoriaProdutos;

    @OneToMany(mappedBy = "categoriaProdutos")
    private List<Produto> produtos;

    private LocalDate dataCriacao;

    @PrePersist
    protected void onCreate()
    {
        this.dataCriacao = LocalDate.now();
    }


}
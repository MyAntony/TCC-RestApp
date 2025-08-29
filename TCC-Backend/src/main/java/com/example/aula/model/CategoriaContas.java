package com.example.aula.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class CategoriaContas
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome da categoria é obrigatório.")
    private String nomeCategoria;

    private String descricaoCategoria;

    public CategoriaContas()
    {
        
    }

    public CategoriaContas(Long id, @NotBlank(message = "O nome da categoria é obrigatório.") String nomeCategoria, String descricaoCategoria)
    {
        this.id = id;
        this.nomeCategoria = nomeCategoria;
        this.descricaoCategoria = descricaoCategoria;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getNomeCategoria()
    {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria)
    {
        this.nomeCategoria = nomeCategoria;
    }

    public String getDescricaoCategoria()
    {
        return descricaoCategoria;
    }

    public void setDescricaoCategoria(String descricaoCategoria)
    {
        this.descricaoCategoria = descricaoCategoria;
    }

    
}

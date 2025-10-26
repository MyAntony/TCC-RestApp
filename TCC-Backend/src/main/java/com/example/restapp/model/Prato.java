package com.example.restapp.model;

import com.example.restapp.model.produtos.Categoria;
import com.example.restapp.model.produtos.Disponibilidade;

// import com.example.aula.model.Sexo;
import jakarta.persistence.*;
// import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
// import jakarta.validation.constraints.Size;

@Entity
public class Prato
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do prato é obrigatorio.")
    private String nome;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    @NotBlank(message = "Descrição do prato.")
    private String descricao;
    
   
    @NotNull(message = "O preço do prato é obrigatório.")
    private Double preco;

    private String url;

    @Enumerated(EnumType.STRING)
    private Disponibilidade disponibilidade;

    public Prato()
    {

    }

    public Prato(Long id, String nome, Categoria categoria, String descricao, Double preco, String url, Disponibilidade disponibilidade)
    {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.descricao = descricao;
        this.preco = preco;
        this.url = url;
        this.disponibilidade = disponibilidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Disponibilidade getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(Disponibilidade disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

        

    // Getters and Setters

    
}



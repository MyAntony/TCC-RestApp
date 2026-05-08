package com.example.restapp.model.produtos;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter // Gera os getters
@Setter // Gera os setters
@AllArgsConstructor // Gera o construtor com todos os argumentos
@NoArgsConstructor // Gera o construtor sem argumentos

public class Produto
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(nullable = false)
    private String nomeProduto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private CategoriaProdutos categoriaProdutos;

    @JoinColumn(nullable = false)
    private BigDecimal precoCusto;

    @JoinColumn(nullable = false)
    private BigDecimal precoVenda;

    private String descricao;

    private String imagem;

    @CreatedDate
    private LocalDate dataCriacao;

    @PrePersist
    protected void onCreate()
    {
        this.dataCriacao = LocalDate.now();
    }
    
}
package com.example.restapp.model.produtos;

import java.time.LocalDate;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

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

    @NotBlank(message = "O nome do produto é obrigatorio.")
    private String nomeProduto;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private CategoriaProdutos categoriaProdutos;

    @NotNull(message = "O preço de custo do produto é obrigatório.")
    @Min(value = 0, message = "O preço de custo não pode ser negativo.")
    private Double precoCusto;

    @NotNull(message = "O preço de venda do produto é obrigatório.")
    private Double precoVenda;

    private String descricao;
    
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] imagem;

    private LocalDate dataCriacao;

    @PrePersist
    protected void onCreate()
    {
        this.dataCriacao = LocalDate.now();
    }
    
}
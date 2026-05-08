package com.example.restapp.dto.produto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter // Gera os getters
@Setter // Gera os setters
@AllArgsConstructor // Gera o construtor com todos os argumentos
@NoArgsConstructor // Gera o construtor sem argumentos
public class ProdutoRequestDTO
{
    @NotBlank(message = "O nome do produto é obrigatorio.")
    private String nomeProduto;

    @NotNull(message = "O ID da categoria do produto é obrigatório.")
    private Long idCategoriaProdutos;

    @NotNull(message = "O preço de custo do produto é obrigatório.")
    @Min(value = 0, message = "O preço de custo não pode ser negativo.")
    private BigDecimal precoCusto;

    @NotNull(message = "O preço de venda do produto é obrigatório.")
    @Min(value = 0, message = "O preço de venda não pode ser negativo.")
    private BigDecimal precoVenda;
    
    private String descricao;
    private String imagem;
}

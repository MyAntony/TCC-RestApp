package com.example.restapp.dto.produto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoResumoDTO 
{
    private Long id;
    private String nomeProduto;
    private BigDecimal precoUnitario;
    private BigDecimal precoVenda;
}

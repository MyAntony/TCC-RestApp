package com.example.restapp.dto.ProdutoDTO;

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
    private Double precoUnitario;
    private Double precoVenda;
}

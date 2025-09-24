package com.example.aula.model;

// import com.example.aula.model.Sexo;
import jakarta.persistence.*;
// import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
// import jakarta.validation.constraints.Size;
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
    private String nome;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    @NotBlank(message = "Descrição do produto.")
    private String descricao;
    
   
    @NotNull(message = "O preço do produto é obrigatório.")
    private Double preco;

    private String url;

    @Enumerated(EnumType.STRING)
    private Disponibilidade disponibilidade;
    
}
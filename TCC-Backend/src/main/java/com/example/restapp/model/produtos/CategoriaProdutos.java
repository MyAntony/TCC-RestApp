package com.example.restapp.model.produtos;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @JoinColumn(unique = true, nullable = false)
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
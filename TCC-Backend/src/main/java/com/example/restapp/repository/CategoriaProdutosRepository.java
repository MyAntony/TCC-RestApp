package com.example.restapp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.restapp.model.produtos.CategoriaProdutos;

public interface CategoriaProdutosRepository extends JpaRepository<CategoriaProdutos, Long>
{
    List<CategoriaProdutos> findByNomeCategoriaProdutosContainingIgnoreCase(String nomeCategoriaProdutos);
}
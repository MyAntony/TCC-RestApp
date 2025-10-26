package com.example.restapp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.restapp.model.produtos.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>
{
    List<Produto> findByNomeProdutoContainingIgnoreCase(String nomeProduto);
}

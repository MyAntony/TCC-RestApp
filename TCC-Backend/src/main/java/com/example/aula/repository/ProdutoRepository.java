package com.example.aula.repository;

import com.example.aula.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>
{
    // Optional<Produto> findByEmail(String email);
}

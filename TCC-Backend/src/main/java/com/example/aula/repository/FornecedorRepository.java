package com.example.aula.repository;

import com.example.aula.model.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// import java.util.Optional;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long>
{
    // boolean existsByCnpj(String cnpj);
}

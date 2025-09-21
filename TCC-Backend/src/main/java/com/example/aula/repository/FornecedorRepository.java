package com.example.aula.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.aula.model.financeiro.Fornecedor;

// import java.util.Optional;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long>
{
    // boolean existsByCnpj(String cnpj);
}

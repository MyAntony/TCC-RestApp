package com.example.aula.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.aula.model.financeiro.Fornecedor;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long>
{
    List<Fornecedor> findByNomeFantasiaContainingIgnoreCase(String nomeFantasia);
    // boolean existsByCnpj(String cnpj);
}

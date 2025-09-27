package com.example.aula.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.aula.model.principal.cliente.Cliente;

// import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>
{
    // boolean existsByCnpj(String cnpj);
}

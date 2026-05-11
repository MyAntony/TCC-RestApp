package com.example.restapp.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.restapp.model.principal.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>
{
    List<Cliente> findByNomeContainingIgnoreCase(String nome);
    Optional<Cliente> findByIdAuxiliar(UUID idAuxiliar);
}

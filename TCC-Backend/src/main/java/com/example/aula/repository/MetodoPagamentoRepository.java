package com.example.aula.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.aula.model.financeiro.MetodoPagamento;

// import java.util.Optional;

@Repository
public interface MetodoPagamentoRepository extends JpaRepository<MetodoPagamento, Long>
{
    // Optional<Produto> findByEmail(String email);
}

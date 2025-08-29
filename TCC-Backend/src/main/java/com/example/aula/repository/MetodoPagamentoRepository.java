package com.example.aula.repository;

import com.example.aula.model.MetodoPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// import java.util.Optional;

@Repository
public interface MetodoPagamentoRepository extends JpaRepository<MetodoPagamento, Long>
{
    // Optional<Prato> findByEmail(String email);
}

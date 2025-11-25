package com.example.restapp.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.restapp.model.principal.Pagamento;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long>
{
    List<Pagamento> findByMesaSessaoId(Long mesaId);

}
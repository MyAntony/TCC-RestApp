package com.example.aula.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.aula.model.financeiro.ContasPagar;

@Repository
public interface ContasPagarRepository extends JpaRepository<ContasPagar, Long>
{
    
}

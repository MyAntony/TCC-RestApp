package com.example.restapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.restapp.entity.financeiro.ContasPagar;

@Repository
public interface ContasPagarRepository extends JpaRepository<ContasPagar, Long>
{
    
}

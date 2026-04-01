package com.example.restapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.restapp.entity.financeiro.CategoriaContas;

@Repository
public interface CategoriaContasRepository extends JpaRepository<CategoriaContas, Long>
{
    List<CategoriaContas> findByNomeCategoriaContainingIgnoreCase(String nomeCategoria);

}

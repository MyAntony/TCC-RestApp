package com.example.restapp.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.restapp.model.Mesa;


@Repository
public interface MesaRepository extends JpaRepository<Mesa, Long> {
}

package com.example.restapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.restapp.model.Prato;

// import java.util.Optional;

@Repository
public interface PratoRepository extends JpaRepository<Prato, Long>
{
    // Optional<Prato> findByEmail(String email);
}

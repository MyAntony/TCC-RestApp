package com.example.aula.repository;

import com.example.aula.model.Prato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// import java.util.Optional;

@Repository
public interface PratoRepository extends JpaRepository<Prato, Long>
{
    // Optional<Prato> findByEmail(String email);
}

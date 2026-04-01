package com.example.restapp.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.restapp.entity.enums.StatusMesa;
import com.example.restapp.entity.principal.MesaSessao;

@Repository
public interface MesaSessaoRepository extends JpaRepository<MesaSessao, Long>
{
    List<MesaSessao> findByStatus(StatusMesa statusMesa);
    List<MesaSessao> findByStatusIn(List<StatusMesa> statusMesa);
    boolean existsByMesaIdAndStatusIn(Long mesaId, List<StatusMesa> status);
    Optional<MesaSessao> findByMesaIdAndStatus(Long mesaId, StatusMesa status);
}

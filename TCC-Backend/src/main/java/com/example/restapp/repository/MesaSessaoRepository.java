package com.example.restapp.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.restapp.model.enums.StatusMesa;
import com.example.restapp.model.principal.MesaSessao;

@Repository
public interface MesaSessaoRepository extends JpaRepository<MesaSessao, Long>
{
    List<MesaSessao> findByStatus(StatusMesa statusMesa);
    List<MesaSessao> findByStatusIn(List<StatusMesa> statusMesa);
    boolean existsByMesaIdAndStatusIn(Long mesaId, List<StatusMesa> status);
}

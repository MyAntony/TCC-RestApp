package com.example.restapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.restapp.model.principal.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>
{
    
}

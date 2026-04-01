package com.example.restapp.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.example.restapp.entity.principal.Mesa;
import com.example.restapp.repository.MesaRepository;


@Service
public class MesaService
{

    private final MesaRepository mesaRepository;

    public MesaService(MesaRepository mesaRepository)
    {
        this.mesaRepository = mesaRepository;
    }

    // Criar mesa
    public Mesa criar(Mesa mesa)
    {
        if (mesaRepository.existsById(mesa.getId()))
        {
            throw new RuntimeException("Já existe uma mesa com esse número/ID!");
        }
        return mesaRepository.save(mesa);
    }
    
    // Listar todas as mesas
    public List<Mesa> listarTodas()
    {
        return mesaRepository.findAll();
    }

    // Buscar mesa por ID
    public Mesa buscarPorId(Long id)
    {
        return mesaRepository.findById(id).orElseThrow(() -> new RuntimeException("Mesa não encontrada: " + id));
    }
}
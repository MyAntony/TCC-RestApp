package com.example.restapp.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restapp.model.Mesa;
import com.example.restapp.repository.MesaRepository;

@Service
public class MesaService {

    @Autowired
    private MesaRepository repository;

    public List<Mesa> listarMesas() {
        return repository.findAll();
    }

    public Mesa salvar (Mesa mesa) {
        return repository.save(mesa);
    }

    public Mesa atualizar(Long id, Mesa mesaAtualizada) {
        Mesa existente = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Mesa não encontrada"));
            existente.setNumeroMesa(mesaAtualizada.getNumeroMesa());
            existente.setCapacidade(mesaAtualizada.getCapacidade());
            existente.setCapacidade(mesaAtualizada.getCapacidade());
            return repository.save(existente);

    }
    public void remover(Long id) {
        repository.deleteById(id);

        }
        
    }






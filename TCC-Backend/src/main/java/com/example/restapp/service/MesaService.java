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

    
    public List<Mesa> listarTodas() {
        return repository.findAll();
    }

    
    public Mesa buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mesa não encontrada"));
    }

    
    public Mesa salvar(Mesa mesa) {
        return repository.save(mesa);
    }

    
    public Mesa atualizar(Long id, Mesa mesaAtualizada) {
        Mesa existente = buscarPorId(id); // usamos o método buscarPorId para reaproveitar código

        existente.setNumeroMesa(mesaAtualizada.getNumeroMesa());
        existente.setCapacidade(mesaAtualizada.getCapacidade());

        return repository.save(existente);
    }

    
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}

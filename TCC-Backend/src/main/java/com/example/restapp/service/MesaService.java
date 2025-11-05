package com.example.restapp.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restapp.dto.MesaRequestDTO;
import com.example.restapp.dto.MesaResponseDTO;
import com.example.restapp.model.Mesa;
//import com.example.restapp.model.enums.StatusMesa;
import com.example.restapp.repository.MesaRepository;

@Service
public class MesaService {
    
    @Autowired
    private MesaRepository repository;


    
    public List<MesaResponseDTO> listarTodas() {
        return repository.findAll()
        .stream()
        .map(this::toResponseDTO)
        .collect(Collectors.toList());
    }

    
    public MesaResponseDTO salvar(MesaRequestDTO dto) {
        Mesa novaMesa = toEntity(dto);
        Mesa salvar = repository.save(novaMesa);
        return toResponseDTO(salvar);
            }

    
    public MesaResponseDTO atualizar(Long id, MesaRequestDTO dto) {
        Mesa existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mesa não encontrada"));

        existente.setNumeroMesa(dto.getNumeroMesa());
        existente.setCapacidade(dto.getCapacidade());
        existente.setStatus(dto.getStatus());
        existente.setHorarioAbertura(dto.getHorarioAbertura());
        existente.setHorarioFechamento(dto.getHorarioFechamento());
        existente.setGarcomResponsavel(dto.getGarcomResponsavel());

        Mesa atualizada = repository.save(existente);
        return toResponseDTO(atualizada);
    
    }


    
    public void deletar(Long id) {
       repository.deleteById(id);
            
        }
     private Mesa toEntity(MesaRequestDTO dto) {
        Mesa mesa = new Mesa();
        mesa.setNumeroMesa(dto.getNumeroMesa());
        mesa.setCapacidade(dto.getCapacidade());
        mesa.setStatus(dto.getStatus());
        mesa.setHorarioAbertura(dto.getHorarioAbertura());
        mesa.setHorarioFechamento(dto.getHorarioFechamento());
        mesa.setGarcomResponsavel(dto.getGarcomResponsavel());
        return mesa;
     }

    private MesaResponseDTO toResponseDTO(Mesa mesa) {
        return new MesaResponseDTO(
                mesa.getId(),
                mesa.getNumeroMesa(),
                mesa.getCapacidade(),
                mesa.getStatus(),
                mesa.getHorarioAbertura(),
                mesa.getHorarioFechamento(),
                mesa.getGarcomResponsavel()
             );
         }
    }


package com.example.restapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restapp.dto.MesaDTO.MesaRequestDTO;
import com.example.restapp.dto.MesaDTO.MesaResponseDTO;
import com.example.restapp.service.MesaService;

@RestController
@RequestMapping("/mesas")
@CrossOrigin(origins = "*")
public class MesaController {
    
    @Autowired
    private MesaService service;

   // public MesaController(MesaService service) {
     //   this.service = service;

    

    @GetMapping 
    public ResponseEntity<List<MesaResponseDTO>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());

    }
    @PostMapping
    public ResponseEntity<MesaResponseDTO> criarMesa(@RequestBody MesaRequestDTO dto) {
        MesaResponseDTO novaMesa = service.salvar(dto);
                return ResponseEntity.ok(novaMesa);
    
    }

    @PutMapping("/{id}")
    public ResponseEntity<MesaResponseDTO> atualizarMesa(
            @PathVariable Long id,
            @RequestBody MesaRequestDTO dto) {

        MesaResponseDTO mesaAtualizada = service.atualizar(id, dto);
        return ResponseEntity.ok(mesaAtualizada);
            }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMesa(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
    
}


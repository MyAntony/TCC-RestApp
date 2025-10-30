package com.example.restapp.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restapp.model.Mesa;
import com.example.restapp.service.MesaService;

@RestController
@RequestMapping
public class MesaController {
    
    private final MesaService service;

    public MesaController(MesaService service) {
        this.service = service;

    }

    @GetMapping 
    public ResponseEntity<List<Mesa>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());

    }
    @GetMapping("/{id}")
    public ResponseEntity<Mesa> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }
    

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
    
}


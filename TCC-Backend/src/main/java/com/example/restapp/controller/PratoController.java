package com.example.restapp.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.restapp.model.Prato;
import com.example.restapp.service.PratoService;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/pratos")
public class PratoController
{

    private PratoService pratoService;

    public PratoController(PratoService pratoService)
    {
        this.pratoService = pratoService;
    }

    @GetMapping
    public List<Prato> listarTodos()
    {
        return pratoService.listarTodos();
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> salvar(@Valid @RequestBody Prato prato)
    {
        pratoService.salvar(prato);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Map.of("mensagem", "Prato cadastrado com sucesso!"));
    }

    @PutMapping
    public ResponseEntity<Map<String, Object>> atualizar(@Valid @RequestBody Prato prato)
    {
        pratoService.atualizar(prato);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Map.of("mensagem", "Prato atualizado com sucesso"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> excluir(@PathVariable Long id)
    {
        pratoService.excluir(id); 
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Map.of("mensagem", "Prato excluído com sucesso"));
    }
}

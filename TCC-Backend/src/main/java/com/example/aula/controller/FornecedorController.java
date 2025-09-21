package com.example.aula.controller;

import com.example.aula.model.financeiro.Fornecedor;
import com.example.aula.service.FornecedorService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/financeiro/fornecedor")
public class FornecedorController
{

    private FornecedorService fornecedorService;

    public FornecedorController(FornecedorService fornecedorService)
    {
        this.fornecedorService = fornecedorService;
    }

    @GetMapping
    public List<Fornecedor> listarTodos()
    {
        return fornecedorService.listarTodos();
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> salvar(@Valid @RequestBody Fornecedor fornecedor)
    {
        fornecedorService.salvar(fornecedor);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Map.of("mensagem", "Fornecedor cadastrado com sucesso!"));
    }

    @PutMapping
    public ResponseEntity<Map<String, Object>> atualizar(@Valid @RequestBody Fornecedor fornecedor)
    {
        fornecedorService.atualizar(fornecedor);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Map.of("mensagem", "Fornecedor atualizado com sucesso"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> excluir(@PathVariable Long id)
    {
        fornecedorService.excluir(id); 
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Map.of("mensagem", "Fornecedor excluído com sucesso"));
    }


}

package com.example.aula.controller;

import com.example.aula.dto.FornecedorDTO.FornecedorRequestDTO;
import com.example.aula.dto.FornecedorDTO.FornecedorResponseDTO;
// import com.example.aula.model.financeiro.Fornecedor;
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

    @PostMapping // Create - Crud
    // @ResponseStatus(HttpStatus.CREATED) /* <-- utilizar caso não queira utilizar o ResponseEntity */
    public ResponseEntity<Map<String, Object>> salvar(@Valid @RequestBody FornecedorRequestDTO fornecedorRequestDTO)
    {
        fornecedorService.salvar(fornecedorRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("mensagem", "Fornecedor cadastrado com sucesso!"));
    }

    @GetMapping // Read - cRud
    public List<FornecedorResponseDTO> listarTodos()
    {
        return fornecedorService.listarTodos();
    }

    @GetMapping("/buscar") // Read com filtro - cRud
    public List<FornecedorResponseDTO> buscarPorNome(@RequestParam String nomeFantasia)
    {
        return fornecedorService.buscarPorNome(nomeFantasia);
    }

    @PutMapping // Update crUd
    public ResponseEntity<Map<String, Object>> atualizar(@Valid @RequestBody FornecedorRequestDTO fornecedorRequestDTO)
    {
        fornecedorService.atualizar(fornecedorRequestDTO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Map.of("mensagem", "Fornecedor atualizado com sucesso"));
    }

    @DeleteMapping("/{id}") // Delete - cruD
    public ResponseEntity<Map<String, Object>> excluir(@PathVariable Long id)
    {
        fornecedorService.excluir(id); 
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Map.of("mensagem", "Fornecedor excluído com sucesso"));
    }


}

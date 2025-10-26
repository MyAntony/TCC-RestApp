package com.example.restapp.controller.produtos;

import com.example.restapp.dto.CategoriaProdutosDTO.CategoriaProdutosRequestDTO;
import com.example.restapp.dto.CategoriaProdutosDTO.CategoriaProdutosResponseDTO;
import com.example.restapp.service.CategoriaProdutosService;
import java.util.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("produtos/categoria-produtos")
public class CategoriaProdutosController
{
    private CategoriaProdutosService categoriaProdutosService;

    public CategoriaProdutosController(CategoriaProdutosService categoriaProdutosService)
    {
        this.categoriaProdutosService = categoriaProdutosService;
    }

    @PostMapping // Create - Crud
    public ResponseEntity<Map<String, Object>> salvar(@Valid @RequestBody CategoriaProdutosRequestDTO categoriaProdutosRequestDTO)
    {
        categoriaProdutosService.salvar(categoriaProdutosRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("mensagem", "CategoriaProdutos cadastrado com sucesso!"));
    }

    @GetMapping // Read - cRud
    public List<CategoriaProdutosResponseDTO> listarTodos()
    {
        return categoriaProdutosService.listarTodos();
    }

    @GetMapping("/buscar") // Read com filtro - cRud
    public List<CategoriaProdutosResponseDTO> buscarPorNome(@RequestParam String nomeCategoriaProdutos)
    {
        return categoriaProdutosService.buscarPorNome(nomeCategoriaProdutos);
    }

    @PutMapping("/{id}") // Update crUd
    public ResponseEntity<Map<String, Object>> atualizar(@PathVariable Long id, @Valid @RequestBody CategoriaProdutosRequestDTO categoriaProdutosRequestDTO) 
    {
        categoriaProdutosService.atualizar(id, categoriaProdutosRequestDTO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Map.of("mensagem", "CategoriaProdutos atualizado com sucesso"));
    }

    @DeleteMapping("/{id}") // Delete - cruD
    public ResponseEntity<Map<String, Object>> excluir(@PathVariable Long id)
    {
        categoriaProdutosService.excluir(id); 
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Map.of("mensagem", "CategoriaProdutos excluído com sucesso"));
    }
}

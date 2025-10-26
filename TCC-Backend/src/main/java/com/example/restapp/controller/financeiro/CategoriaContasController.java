package com.example.restapp.controller.financeiro;

import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.example.restapp.dto.CategoriaContasDTO.CategoriaContasRequestDTO;
import com.example.restapp.dto.CategoriaContasDTO.CategoriaContasResponseDTO;
import com.example.restapp.service.CategoriaContasService;

import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/financeiro/categoria-contas")
public class CategoriaContasController
{

    private CategoriaContasService categoriaContasService;

    public CategoriaContasController(CategoriaContasService categoriaContasService)
    {
        this.categoriaContasService = categoriaContasService;
    }

    @PostMapping // Create - Crud
    // @ResponseStatus(HttpStatus.CREATED) /* <-- utilizar caso não queira utilizar o ResponseEntity */
    public ResponseEntity<Map<String, Object>> salvar(@Valid @RequestBody CategoriaContasRequestDTO categoriaContasRequestDTO)
    {
        categoriaContasService.salvar(categoriaContasRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("mensagem", "CategoriaContas cadastrado com sucesso!"));
    }

    @GetMapping // Read - cRud
    public List<CategoriaContasResponseDTO> listarTodos()
    {
        return categoriaContasService.listarTodos();
    }

    @GetMapping("/buscar") // Read com filtro - cRud
    public List<CategoriaContasResponseDTO> buscarPorNome(@RequestParam String nomeCategoria)
    {
        return categoriaContasService.buscarPorNome(nomeCategoria);
    }

    @PutMapping("/{id}") // Update crUd
    public ResponseEntity<Map<String, Object>> atualizar(@PathVariable Long id, @Valid @RequestBody CategoriaContasRequestDTO categoriaContasRequestDTO) 
    {
        categoriaContasService.atualizar(id, categoriaContasRequestDTO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Map.of("mensagem", "CategoriaContas atualizado com sucesso"));
    }

    @DeleteMapping("/{id}") // Delete - cruD
    public ResponseEntity<Map<String, Object>> excluir(@PathVariable Long id)
    {
        categoriaContasService.excluir(id); 
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Map.of("mensagem", "CategoriaContas excluído com sucesso"));
    }


}

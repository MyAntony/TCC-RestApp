package com.example.restapp.controller.produtos;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.restapp.dto.categoriaprodutos.CategoriaProdutosRequestDTO;
import com.example.restapp.dto.categoriaprodutos.CategoriaProdutosResponseDTO;
import com.example.restapp.service.CategoriaProdutosService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("produtos/categoria-produtos")
public class CategoriaProdutosController
{
    @Autowired
    private CategoriaProdutosService categoriaProdutosService;

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

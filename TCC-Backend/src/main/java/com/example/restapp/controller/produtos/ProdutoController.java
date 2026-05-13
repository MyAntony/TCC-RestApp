package com.example.restapp.controller.produtos;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

import com.example.restapp.dto.produto.ProdutoRequestDTO;
import com.example.restapp.dto.produto.ProdutoResponseDTO;
import com.example.restapp.service.ProdutoService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/produtos/produto")
public class ProdutoController
{
    @Autowired
    private ProdutoService produtoService;

    @PostMapping // Create - Crud
    // @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<Map<String, Object>> salvar(@Valid @RequestBody ProdutoRequestDTO produtoRequestDTO)
    {
        produtoService.salvar(produtoRequestDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Map.of("mensagem", "Produto cadastrado com sucesso!"));
    }
    
    @GetMapping // Read - cRud
    public List<ProdutoResponseDTO> listarTodos()
    {
        return produtoService.listarTodos();
    }

    @GetMapping("/buscar") // Read com filtro - cRud
    public List<ProdutoResponseDTO> buscarPorNome(@RequestParam String nomeProduto)
    {
        return produtoService.buscarPorNome(nomeProduto);
    }

    @PutMapping("/{id}") // Update crUd
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<Map<String, Object>> atualizar(@PathVariable Long id, @Valid @RequestBody ProdutoRequestDTO produtoRequestDTO)
    {
        produtoService.atualizar(id, produtoRequestDTO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Map.of("mensagem", "Produto atualizado com sucesso"));
    }

    @DeleteMapping("/{id}") // Delete - cruD
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<Map<String, Object>> excluir(@PathVariable Long id)
    {
        produtoService.excluir(id); 
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Map.of("mensagem", "Produto excluído com sucesso"));
    }
}

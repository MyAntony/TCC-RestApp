package com.example.aula.controller;

import com.example.aula.model.Produto;
import com.example.aula.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import org.springframework.security.access.prepost.PreAuthorize;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/produtos")
public class ProdutoController
{

    private ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService)
    {
        this.produtoService = produtoService;
    }

    @GetMapping
    public List<Produto> listarTodos()
    {
        return produtoService.listarTodos();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<Map<String, Object>> salvar(@Valid @RequestBody Produto produto)
    {
        produtoService.salvar(produto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Map.of("mensagem", "Produto cadastrado com sucesso!"));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<Map<String, Object>> atualizar(@PathVariable Long id, @Valid @RequestBody Produto produto)
    {
        produtoService.atualizar(id, produto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Map.of("mensagem", "Produto atualizado com sucesso"));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<Map<String, Object>> excluir(@PathVariable Long id)
    {
        produtoService.excluir(id); 
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Map.of("mensagem", "Produto excluído com sucesso"));
    }
}

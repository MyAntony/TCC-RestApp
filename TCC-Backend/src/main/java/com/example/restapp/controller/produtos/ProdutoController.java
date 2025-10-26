package com.example.restapp.controller.produtos;

import com.example.restapp.dto.ProdutoDTO.ProdutoRequestDTO;
import com.example.restapp.dto.ProdutoDTO.ProdutoResponseDTO;
import com.example.restapp.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import org.springframework.security.access.prepost.PreAuthorize;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/produtos/produto")
public class ProdutoController
{

    private ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService)
    {
        this.produtoService = produtoService;
    }

    @PostMapping // Create - Crud
    @PreAuthorize("hasRole('ADMINISTRADOR')")
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

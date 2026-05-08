package com.example.restapp.controller.financeiro;

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

import com.example.restapp.dto.categoriacontas.CategoriaContasRequestDTO;
import com.example.restapp.dto.categoriacontas.CategoriaContasResponseDTO;
import com.example.restapp.service.CategoriaContasService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/financeiro/categoria-contas")
public class CategoriaContasController
{
    @Autowired
    private CategoriaContasService categoriaContasService;

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

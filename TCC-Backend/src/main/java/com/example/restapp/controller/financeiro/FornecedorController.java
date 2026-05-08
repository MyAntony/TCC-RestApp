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

import com.example.restapp.dto.fornecedor.FornecedorRequestDTO;
import com.example.restapp.dto.fornecedor.FornecedorResponseDTO;
import com.example.restapp.service.FornecedorService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/financeiro/fornecedor")
public class FornecedorController
{
    @Autowired
    private FornecedorService fornecedorService;

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

    @PutMapping("/{id}") // Update crUd
    public ResponseEntity<Map<String, Object>> atualizar(@PathVariable Long id, @Valid @RequestBody FornecedorRequestDTO fornecedorRequestDTO)
    {
        fornecedorService.atualizar(id, fornecedorRequestDTO);
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

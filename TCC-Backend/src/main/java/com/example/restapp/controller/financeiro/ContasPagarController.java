package com.example.restapp.controller.financeiro;

import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.example.restapp.dto.ContasPagarDTO.ContasPagarRequestDTO;
import com.example.restapp.dto.ContasPagarDTO.ContasPagarResponseDTO;
import com.example.restapp.service.ContasPagarService;

import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/financeiro/contas-pagar")
public class ContasPagarController
{

    private ContasPagarService contasPagarService;

    public ContasPagarController(ContasPagarService contasPagarService)
    {
        this.contasPagarService = contasPagarService;
    }

    @PostMapping // Create - Crud
    // @ResponseStatus(HttpStatus.CREATED) /* <-- utilizar caso não queira utilizar o ResponseEntity */
    public ResponseEntity<Map<String, Object>> salvar(@Valid @RequestBody ContasPagarRequestDTO contasPagarRequestDTO)
    {
        contasPagarService.salvar(contasPagarRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("mensagem", "ContasPagar cadastrado com sucesso!"));
    }

    @GetMapping // Read - cRud
    public List<ContasPagarResponseDTO> listarTodos()
    {
        return contasPagarService.listarTodos();
    }

    // @GetMapping("/buscar") // Read com filtro - cRud /* Não utilizado no momento */
    // public List<ContasPagarResponseDTO> buscarPorNome(@RequestParam String nomeFantasia) /* Não utilizado no momento */
    // { /* Não utilizado no momento */
    //     return contasPagarService.buscarPorNome(nomeFantasia); /* Não utilizado no momento */
    // } /* Não utilizado no momento */

    @PutMapping("/{id}") // Update crUd
    public ResponseEntity<Map<String, Object>> atualizar(@PathVariable Long id, @Valid @RequestBody ContasPagarRequestDTO contasPagarRequestDTO)
    {
        contasPagarService.atualizar(id, contasPagarRequestDTO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Map.of("mensagem", "Despesa atualizado com sucesso"));
    }

    @DeleteMapping("/{id}") // Delete - cruD
    public ResponseEntity<Map<String, Object>> excluir(@PathVariable Long id)
    {
        contasPagarService.excluir(id); 
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Map.of("mensagem", "ContasPagar excluído com sucesso"));
    }


}

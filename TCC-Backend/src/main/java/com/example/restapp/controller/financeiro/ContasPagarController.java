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
import org.springframework.web.bind.annotation.RestController;

import com.example.restapp.dto.contaspagar.ContasPagarRequestDTO;
import com.example.restapp.dto.contaspagar.ContasPagarResponseDTO;
import com.example.restapp.service.ContasPagarService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/financeiro/contas-pagar")
public class ContasPagarController
{
    @Autowired
    private ContasPagarService contasPagarService;

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

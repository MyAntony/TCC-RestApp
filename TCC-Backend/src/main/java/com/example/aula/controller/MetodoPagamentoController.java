package com.example.aula.controller;

import com.example.aula.model.financeiro.MetodoPagamento;
import com.example.aula.service.MetodoPagamentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/financeiro/metodo-pagamento")
public class MetodoPagamentoController
{

    private MetodoPagamentoService metodoPagamentoService;

    public MetodoPagamentoController(MetodoPagamentoService metodoPagamentoService)
    {
        this.metodoPagamentoService = metodoPagamentoService;
    }

    @GetMapping
    public List<MetodoPagamento> listarTodos()
    {
        return metodoPagamentoService.listarTodos();
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> salvar(@Valid @RequestBody MetodoPagamento metodoPagamento)
    {
        metodoPagamentoService.salvar(metodoPagamento);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Map.of("mensagem", "Método de Pagamento cadastrado com sucesso!"));
    }

    @PutMapping
    public ResponseEntity<Map<String, Object>> atualizar(@Valid @RequestBody MetodoPagamento metodoPagamento)
    {
        metodoPagamentoService.atualizar(metodoPagamento);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Map.of("mensagem", "Método de Pagamento atualizado com sucesso"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> excluir(@PathVariable Long id)
    {
        metodoPagamentoService.excluir(id); 
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Map.of("mensagem", "Método de Pagamento excluído com sucesso"));
    }


}

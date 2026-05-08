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

import com.example.restapp.model.financeiro.MetodoPagamento;
import com.example.restapp.service.MetodoPagamentoService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/financeiro/metodo-pagamento")
public class MetodoPagamentoController
{
    @Autowired
    private MetodoPagamentoService metodoPagamentoService;

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

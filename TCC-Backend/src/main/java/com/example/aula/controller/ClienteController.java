package com.example.aula.controller;

import com.example.aula.model.cliente.Cliente;
import com.example.aula.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("principal/clientes")
public class ClienteController
{
    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService)
    {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<Cliente> listarTodos()
    {
        return clienteService.listarTodos();
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> salvar(@Valid @RequestBody Cliente cliente)
    {
        clienteService.salvar(cliente);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Map.of("mensagem", "Cliente cadastrado com sucesso!"));
    }

    @PutMapping
    public ResponseEntity<Map<String, Object>> atualizar(@Valid @RequestBody Cliente cliente)
    {
        clienteService.atualizar(cliente);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Map.of("mensagem", "Cliente atualizado com sucesso"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> excluir(@PathVariable Long id)
    {
        clienteService.excluir(id); 
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Map.of("mensagem", "Cliente excluído com sucesso"));
    }
}

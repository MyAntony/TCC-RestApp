package com.example.restapp.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import org.springframework.web.bind.annotation.RestController;

import com.example.restapp.dto.cliente.ClienteRequestDTO;
import com.example.restapp.dto.cliente.ClienteResponseDTO;
import com.example.restapp.service.ClienteService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("principal/clientes")
public class ClienteController
{
    
    @Autowired
    private ClienteService clienteService;

    // Create
    @PostMapping
    public ResponseEntity<Map<String, Object>> salvar(@Valid @RequestBody ClienteRequestDTO clienteRequestDTO)
    {
        clienteService.salvar(clienteRequestDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Map.of("mensagem", "Cliente cadastrado com sucesso!"));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public List<ClienteResponseDTO> listarTodos()
    {
        return clienteService.listarTodos();
    }

    @GetMapping("/{idAuxiliar}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<ClienteResponseDTO> buscarPorIdAuxiliar(@PathVariable UUID idAuxiliar)
    {
        ClienteResponseDTO clienteResponseDTO = clienteService.buscarPorIdAuxiliar(idAuxiliar);
        return ResponseEntity.ok(clienteResponseDTO);
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @PutMapping("{idAuxiliar}")
    public ResponseEntity<Map<String, Object>> atualizar(@PathVariable UUID idAuxiliar, @Valid @RequestBody ClienteRequestDTO clienteRequestDTO)
    {
        clienteService.atualizar(idAuxiliar, clienteRequestDTO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Map.of("mensagem", "Cliente atualizado com sucesso"));
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> excluir(@PathVariable Long id)
    {
        clienteService.excluir(id); 
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Map.of("mensagem", "Cliente excluído com sucesso"));
    }
}

package com.example.restapp.controller.principal;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.example.restapp.dto.pedido.*;
import com.example.restapp.service.*;
import jakarta.validation.*;

@RestController
@RequestMapping("principal/pedidos")
public class PedidoController
{
    @Autowired
    private PedidoService pedidoService;

    // Criar pedido para uma mesa específica
    @PostMapping("/mesa/{idMesa}")
    public ResponseEntity<Map<String, Object>> salvar(@PathVariable Long idMesa, @Valid @RequestBody PedidoRequestDTO pedidoRequestDTO)
    {
        pedidoService.salvar(idMesa, pedidoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("mensagem", "Pedido cadastrado com sucesso!"));
    }

    // Listar todos os pedidos
    @GetMapping
    public List<PedidoResponseDTO> listarTodos()
    {
        return pedidoService.listarTodos();
    }

    // Buscar pedido pelo ID
    @GetMapping("/{id}")
    public PedidoResponseDTO buscarPorId(@PathVariable Long id) {
        return pedidoService.buscarPorId(id);
    }

    // Atualizar pedido de uma mesa
    @PutMapping("/mesa/{idMesa}/{id}")
    public ResponseEntity<Map<String, Object>> atualizar(@PathVariable Long idMesa, @PathVariable Long id, @Valid @RequestBody PedidoRequestDTO pedidoRequestDTO)
    {
        pedidoService.atualizar(idMesa, id, pedidoRequestDTO);
        return ResponseEntity.ok(Map.of("mensagem", "Pedido atualizado com sucesso"));
    }

    // Excluir pedido de uma mesa
    @DeleteMapping("/mesa/{idMesa}/{id}")
    public ResponseEntity<Map<String, Object>> excluir(@PathVariable Long idMesa, @PathVariable Long id)
    {
        pedidoService.excluir(id);
        return ResponseEntity.ok(Map.of("mensagem", "Pedido excluído com sucesso"));
    }
}

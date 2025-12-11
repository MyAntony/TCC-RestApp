package com.example.restapp.controller.principal;

import java.util.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.example.restapp.dto.pedido.*;
import com.example.restapp.service.*;
import jakarta.validation.*;

@RestController
@RequestMapping("principal/pedidos")
public class PedidoController
{

    private PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService)
    {
        this.pedidoService = pedidoService;
    }

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

    // LISTAR PEDIDOS DE UMA MESA
    // @GetMapping("/mesa/{idMesa}")
    // public List<Pedido> listarPorMesa(@PathVariable Long idMesa) {
    //     return pedidoService.listarPorMesa(idMesa);
    // }

    // Atualizar pedido de uma mesa
    @PutMapping("/mesa/{idMesa}/{id}")
    public ResponseEntity<Map<String, Object>> atualizar(@PathVariable Long idMesa, @PathVariable Long id, @Valid @RequestBody PedidoRequestDTO pedidoRequestDTO)
    {

        pedidoService.atualizar(idMesa, pedidoRequestDTO, id);
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

package com.example.restapp.controller.principal;

import java.util.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.example.restapp.dto.PedidoDTO.*;
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

    @PostMapping // Create - Crud
    public ResponseEntity<Map<String, Object>> salvar(@Valid @RequestBody PedidoRequestDTO pedidoRequestDTO)
    {
        pedidoService.salvar(pedidoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("mensagem", "Pedido cadastrado com sucesso!"));
    }

    @GetMapping // Read - cRud
    public List<PedidoResponseDTO> listarTodos()
    {
        return pedidoService.listarTodos();
    }

    @GetMapping("/{id}") // Read - com filtro - cRud
    public PedidoResponseDTO buscarPorId(@PathVariable Long id)
    {
        return pedidoService.buscarPorId(id);
    }

    @PutMapping("/{id}") // Update - crUd
    public ResponseEntity<Map<String, Object>> atualizar(@PathVariable Long id, @Valid @RequestBody PedidoRequestDTO pedidoRequestDTO)
    {
        pedidoService.atualizar(pedidoRequestDTO, id);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("mensagem", "Pedido atualizado com sucesso"));
    }

    @DeleteMapping("/{id}") // Delete - cruD
    public ResponseEntity<Map<String, Object>> excluir(@PathVariable Long id)
    {
        pedidoService.excluir(id); 
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("mensagem", "Pedido excluído com sucesso"));
    }
}

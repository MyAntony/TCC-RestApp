package com.example.restapp.controller.principal;

import java.util.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.example.restapp.dto.pagamento.*;
import com.example.restapp.service.*;
import jakarta.validation.*;

@RestController
@RequestMapping("principal/pagamentos")
public class PagamentoController
{

    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService)
    {
        this.pagamentoService = pagamentoService;
    }

    // Criar pagamento para uma mesa específica
    @PostMapping("/mesa/{idMesa}")
    public ResponseEntity<Map<String, Object>> salvar(@PathVariable Long idMesa, @Valid @RequestBody PagamentoRequestDTO pagamentoRequestDTO)
    {
        pagamentoService.salvarPagamento(idMesa, pagamentoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("mensagem", "Pagamento registrado com sucesso!"));
    }

    // Listar todos os pagamentos
    @GetMapping
    public List<PagamentoResponseDTO> listarTodos() {
        return pagamentoService.listarTodos();
    }

    // Buscar pagamento por ID
    @GetMapping("/{id}")
    public PagamentoResponseDTO buscarPorId(@PathVariable Long id) {
        return pagamentoService.buscarPorId(id);
    }

    // Listar pagamentos de uma mesa específica
    @GetMapping("/mesa/{idMesa}")
    public List<PagamentoResponseDTO> listarPagamentosPorMesa(@PathVariable Long idMesa) {
        return pagamentoService.listarPagamentosPorMesa(idMesa);
    }

    // Atualizar pagamento
    // @PutMapping("/{id}")
    // public ResponseEntity<Map<String, Object>> atualizarPagamento(
    //         @PathVariable Long id,
    //         @Valid @RequestBody PagamentoRequestDTO pagamentoRequestDTO) {

    //     pagamentoService.atualizarPagamento(id, pagamentoRequestDTO);
    //     return ResponseEntity.ok(Map.of("mensagem", "Pagamento atualizado com sucesso!"));
    // }

    // Excluir pagamento
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deletarPagamento(@PathVariable Long id) {
        pagamentoService.deletarPagamento(id);
        return ResponseEntity.ok(Map.of("mensagem", "Pagamento excluído com sucesso!"));
    }
}

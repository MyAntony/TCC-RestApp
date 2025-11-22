package com.example.restapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.restapp.dto.PagamentoDTO;
import com.example.restapp.service.PagamentoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pagamentos")
@CrossOrigin(origins = "*")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }


    // ============================================================
    // LISTAR TODOS OS PAGAMENTOS (DTO)
    // ============================================================
    @GetMapping
    public ResponseEntity<List<PagamentoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(pagamentoService.listarPagamentos());
    }


    // ============================================================
    // BUSCAR POR ID (DTO)
    // ============================================================
    @GetMapping("/{id}")
    public ResponseEntity<PagamentoResponseDTO> buscarPorId(@PathVariable Long id) {

        Optional<PagamentoResponseDTO> pagamento = pagamentoService.buscarPorId(id);

        return pagamento
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    // ============================================================
    // CRIAR PAGAMENTO (RequestDTO -> ResponseDTO)
    // ============================================================
    @PostMapping
    public ResponseEntity<PagamentoResponseDTO> salvar(@RequestBody PagamentoRequestDTO dto) {

        PagamentoResponseDTO novoPagamento = pagamentoService.salvarPagamento(dto);

        return ResponseEntity.ok(novoPagamento);
    }


    // ============================================================
    // DELETAR PAGAMENTO
    // ============================================================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        pagamentoService.deletarPagamento(id);
        return ResponseEntity.noContent().build();
    }
}

package com.example.restapp.controller.principal;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restapp.dto.pagamento.PagamentoRequestDTO;
import com.example.restapp.dto.pagamento.PagamentoResponseDTO;
import com.example.restapp.service.PagamentoService;

import jakarta.validation.Valid;

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
    public List<PagamentoResponseDTO> listarTodos()
    {
        return pagamentoService.listarTodos();
    }

    // Buscar pagamento por ID
    @GetMapping("/{id}")
    public PagamentoResponseDTO buscarPorId(@PathVariable Long id)
    {
        return pagamentoService.buscarPorId(id);
    }

    // Listar pagamentos de uma mesa específica
    @GetMapping("/mesa/{idMesa}")
    public List<PagamentoResponseDTO> listarPagamentosPorMesa(@PathVariable Long idMesa)
    {
        return pagamentoService.listarPagamentosPorMesa(idMesa);
    }

    // Excluir pagamento
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deletarPagamento(@PathVariable Long id) {
        pagamentoService.deletarPagamento(id);
        return ResponseEntity.ok(Map.of("mensagem", "Pagamento excluído com sucesso!"));
    }
}

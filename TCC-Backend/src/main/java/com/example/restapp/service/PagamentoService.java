package com.example.restapp.service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import com.example.restapp.model.Pagamento;
import com.example.restapp.repository.PagamentoRepository;

@Service
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;

    public PagamentoService(PagamentoRepository pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
    }

    // Listar todos
    public List<Pagamento> listarPagamentos() {
        return pagamentoRepository.findAll();
    }

    // Buscar por ID com validação
    public Optional<Pagamento> buscarPorId(Long id) {
        return pagamentoRepository.findById(id);
    }

    // Salvar ou atualizar
    public Pagamento salvarPagamento(Pagamento pagamento) {
        return pagamentoRepository.save(pagamento);
    }

    // Deletar com verificação
    public void deletarPagamento(Long id) {
        if (pagamentoRepository.existsById(id)) {
            pagamentoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Pagamento não encontrado com ID: " + id);
        }
    }
}

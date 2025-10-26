package com.example.restapp.service;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.example.restapp.model.financeiro.MetodoPagamento;
import com.example.restapp.repository.MetodoPagamentoRepository;

import java.util.List;

@Service
@Validated
public class MetodoPagamentoService
{
    private MetodoPagamentoRepository metodoPagamentoRepository;

    public MetodoPagamentoService(MetodoPagamentoRepository metodoPagamentoRepository)
    {
        this.metodoPagamentoRepository = metodoPagamentoRepository;
    }

    public List<MetodoPagamento> listarTodos()
    {
        return metodoPagamentoRepository.findAll();
    }

    public MetodoPagamento salvar(@Valid MetodoPagamento metodoPagamento)
    {
        return metodoPagamentoRepository.save(metodoPagamento);
    }

    public MetodoPagamento atualizar(@Valid MetodoPagamento metodoPagamento)
    {
        MetodoPagamento metodoPagamentoAtualizar = metodoPagamentoRepository.findById(metodoPagamento.getId())
                .orElseThrow(() -> new IllegalArgumentException("MetodoPagamento não encontrado."));

        metodoPagamentoAtualizar.setNomeMetodoPagamento(metodoPagamento.getNomeMetodoPagamento());
        metodoPagamentoAtualizar.setTipoMetodoPagamento(metodoPagamento.getTipoMetodoPagamento());
        metodoPagamentoAtualizar.setBandeira(metodoPagamento.getBandeira());

        return metodoPagamentoRepository.save(metodoPagamentoAtualizar);
    }

    public void excluir(Long id)
    {
        MetodoPagamento metodoPagamentoExcluir = metodoPagamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("pedido não encontrado."));
        // Verifica se o metodoPagamento está em estoque antes de excluir

        metodoPagamentoRepository.deleteById(metodoPagamentoExcluir.getId());
    }

}

package com.example.restapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restapp.dto.pagamento.PagamentoRequestDTO;
import com.example.restapp.dto.pagamento.PagamentoResponseDTO;
import com.example.restapp.model.financeiro.MetodoPagamento;
import com.example.restapp.model.principal.MesaSessao;
import com.example.restapp.model.principal.Pagamento;
import com.example.restapp.repository.MesaSessaoRepository;
import com.example.restapp.repository.MetodoPagamentoRepository;
import com.example.restapp.repository.PagamentoRepository;

import jakarta.validation.Valid;

@Service
public class PagamentoService
{
    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private MesaSessaoRepository mesaSessaoRepository;

    @Autowired
    private MetodoPagamentoRepository metodoPagamentoRepository;

    // Create
    public PagamentoResponseDTO salvarPagamento(Long idMesaSessao, @Valid PagamentoRequestDTO pagamentoRequestDTO)
    {
        MesaSessao mesaSessao = mesaSessaoRepository.findById(idMesaSessao)
            .orElseThrow(() -> new RuntimeException("MesaSessao não encontrada"));

        MetodoPagamento metodoPagamento = metodoPagamentoRepository
            .findById(pagamentoRequestDTO.getMetodoPagamentoId())
            .orElseThrow(() -> new RuntimeException("Método de pagamento não encontrado"));

        Pagamento pagamento = new Pagamento();
        pagamento.setMesaSessao(mesaSessao);
        pagamento.setValorPagamento(pagamentoRequestDTO.getValorPagamento());
        pagamento.setMetodoPagamento(metodoPagamento);

        return toResponseDTO(pagamentoRepository.save(pagamento));
    }

    // Read pagamento específico por ID
    public PagamentoResponseDTO buscarPorId(Long id)
    {
        Pagamento pagamento = pagamentoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pagamento não encontrado com ID: " + id));
        return toResponseDTO(pagamento);
    }

    // Read todos os pagamentos
    public List<PagamentoResponseDTO> listarTodos()
    {
        return pagamentoRepository.findAll().stream().map(this::toResponseDTO).toList();
    }

    // Read por Mesa
    public List<PagamentoResponseDTO> listarPagamentosPorMesa(Long idMesaSessao)
    {
        return pagamentoRepository.findByMesaSessaoId(idMesaSessao).stream()
                .map(this::toResponseDTO)
                .toList();
    }

    // Update
    public PagamentoResponseDTO atualizarPagamento(Long idMesaSessao, Long id, @Valid PagamentoRequestDTO pagamentoRequestDTO)
    {
        Pagamento pagamentoExistente = pagamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pagamento não encontrado com ID: " + id));

        MesaSessao mesaSessao = mesaSessaoRepository.findById(idMesaSessao)
                .orElseThrow(() -> new RuntimeException("MesaSessao não encontrada"));

        MetodoPagamento metodoPagamento = metodoPagamentoRepository.findById(pagamentoRequestDTO.getMetodoPagamentoId())
                .orElseThrow(() -> new RuntimeException("Método de pagamento não encontrado"));

        pagamentoExistente.setMesaSessao(mesaSessao);
        pagamentoExistente.setValorPagamento(pagamentoRequestDTO.getValorPagamento());
        pagamentoExistente.setMetodoPagamento(metodoPagamento);

        return toResponseDTO(pagamentoRepository.save(pagamentoExistente));
    }

    // Delete
    public void deletarPagamento(Long id)
    {
        Pagamento pagamentoExistente = pagamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pagamento não encontrado com ID: " + id));

        pagamentoRepository.delete(pagamentoExistente);
    }

    private PagamentoResponseDTO toResponseDTO(Pagamento pagamento)
    {
        PagamentoResponseDTO pagamentoResponseDTO = new PagamentoResponseDTO();
        pagamentoResponseDTO.setId(pagamento.getId());
        pagamentoResponseDTO.setUsuario(pagamento.getUsuario().getNome());
        pagamentoResponseDTO.setNumeroMesa(pagamento.getMesaSessao().getMesa().getId());
        pagamentoResponseDTO.setValorPagamento(pagamento.getValorPagamento());
        pagamentoResponseDTO.setNomeMetodoPagamento(pagamento.getMetodoPagamento().getNomeMetodoPagamento());
        pagamentoResponseDTO.setHorarioLancamento(pagamento.getHorarioLancamento());

        return pagamentoResponseDTO;
    }
}

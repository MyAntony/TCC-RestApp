package com.example.restapp.service;

import com.example.restapp.model.Pagamento;
import com.example.restapp.model.Usuario;
import com.example.restapp.model.principal.Mesa;
import com.example.restapp.model.financeiro.MetodoPagamento;
import com.example.restapp.model.dto.pagamento.PagamentoRequestDTO;
import com.example.restapp.model.dto.pagamento.PagamentoResponseDTO;

import com.example.restapp.repository.PagamentoRepository;
import com.example.restapp.repository.UsuarioRepository;
import com.example.restapp.repository.MesaRepository;
import com.example.restapp.repository.MetodoPagamentoRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final UsuarioRepository usuarioRepository;
    private final MesaRepository mesaRepository;
    private final MetodoPagamentoRepository metodoPagamentoRepository;


    public PagamentoService(
            PagamentoRepository pagamentoRepository,
            UsuarioRepository usuarioRepository,
            MesaRepository mesaRepository,
            MetodoPagamentoRepository metodoPagamentoRepository
    ) {
        this.pagamentoRepository = pagamentoRepository;
        this.usuarioRepository = usuarioRepository;
        this.mesaRepository = mesaRepository;
        this.metodoPagamentoRepository = metodoPagamentoRepository;
    }


    // ============================================================
    // LISTAR → usando DTO
    // ============================================================
    public List<PagamentoResponseDTO> listarPagamentos() {
        return pagamentoRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }


    // ============================================================
    // BUSCAR POR ID → usando DTO
    // ============================================================
    public Optional<PagamentoResponseDTO> buscarPorId(Long id) {
        return pagamentoRepository.findById(id)
                .map(this::toResponseDTO);
    }


    // ============================================================
    // SALVAR (CRIAR) → usando RequestDTO
    // ============================================================
    public PagamentoResponseDTO salvarPagamento(PagamentoRequestDTO dto) {

        Pagamento pagamento = toEntity(dto);
        pagamentoRepository.save(pagamento);

        return toResponseDTO(pagamento);
    }


    // ============================================================
    // DELETAR
    // ============================================================
    public void deletarPagamento(Long id) {
        if (pagamentoRepository.existsById(id)) {
            pagamentoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Pagamento não encontrado com ID: " + id);
        }
    }



    // ============================================================
    // CONVERSOR DTO → ENTITY
    // ============================================================
    private Pagamento toEntity(PagamentoRequestDTO dto) {
        Pagamento pagamento = new Pagamento();

        pagamento.setValorPagamento(dto.getValorPagamento());

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Mesa mesa = mesaRepository.findById(dto.getMesaId())
                .orElseThrow(() -> new RuntimeException("Mesa não encontrada"));

        MetodoPagamento metodo = metodoPagamentoRepository.findById(dto.getMetodoPagamentoId())
                .orElseThrow(() -> new RuntimeException("Método de pagamento não encontrado"));

        pagamento.setUsuario(usuario);
        pagamento.setMesa(mesa);
        pagamento.setMetodoPagamento(metodo);

        return pagamento;
    }


    // ============================================================
    // CONVERSOR ENTITY → RESPONSE DTO
    // ============================================================
    private PagamentoResponseDTO toResponseDTO(Pagamento pagamento) {

        PagamentoResponseDTO dto = new PagamentoResponseDTO();

        dto.setId(pagamento.getId());
        dto.setValorPagamento(pagamento.getValorPagamento());

        dto.setUsuarioId(pagamento.getUsuario().getId());
        dto.setUsuarioNome(pagamento.getUsuario().getNome());

        dto.setMesaId(pagamento.getMesa().getId());
        dto.setNumeroMesa(pagamento.getMesa().getNumero());

        dto.setMetodoPagamentoId(pagamento.getMetodoPagamento().getId());
        dto.setNomeMetodoPagamento(pagamento.getMetodoPagamento().getNome());

        dto.setCriadoPor(pagamento.getCriadoPor());
        dto.setHorarioLancamento(pagamento.getHorarioLancamento());

        return dto;
    }
}

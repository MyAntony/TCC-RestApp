package com.example.restapp.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.example.restapp.dto.mesasessao.MesaSessaoRequestDTO;
import com.example.restapp.dto.mesasessao.MesaSessaoResponseDTO;
import com.example.restapp.dto.pagamento.PagamentoResponseDTO;
import com.example.restapp.dto.pedido.PedidoResumoDTO;
import com.example.restapp.model.Usuario;
import com.example.restapp.model.enums.StatusMesa;
import com.example.restapp.model.principal.Cliente;
import com.example.restapp.model.principal.Mesa;
import com.example.restapp.model.principal.MesaSessao;
import com.example.restapp.repository.ClienteRepository;
import com.example.restapp.repository.MesaRepository;
import com.example.restapp.repository.MesaSessaoRepository;
import com.example.restapp.repository.UsuarioRepository;

import jakarta.validation.Valid;

@Service
@Validated
public class MesaSessaoService
{
    
    @Autowired
    private MesaSessaoRepository mesaSessaoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private MesaRepository mesaRepository;

    @Autowired
    private PagamentoService pagamentoService;

    // Create
    public MesaSessaoResponseDTO salvar(@Valid MesaSessaoRequestDTO MesaSessaoRequestDTO)
    {
        Usuario atendenteResponsavel = null;
        if (MesaSessaoRequestDTO.getIdAtendenteResponsavel() != null)
        {
            atendenteResponsavel = usuarioRepository.findById(MesaSessaoRequestDTO.getIdAtendenteResponsavel())
            .orElseThrow(() -> new RuntimeException("Atendente não encontrado"));
        }

        Cliente cliente = null;
        if (MesaSessaoRequestDTO.getIdCliente() != null)
        {
            cliente = clienteRepository.findById(MesaSessaoRequestDTO.getIdCliente()).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        }

        Mesa mesa = mesaRepository.findById(MesaSessaoRequestDTO.getNumeroMesa()).orElseThrow(() -> new RuntimeException("Mesa não encontrada"));

        List<StatusMesa> statusAbertos = Arrays.asList(StatusMesa.OCUPADA, StatusMesa.FECHAMENTO);

        boolean existeSessaoAberta = mesaSessaoRepository.existsByMesaIdAndStatusIn(MesaSessaoRequestDTO.getNumeroMesa(), statusAbertos);

        if (existeSessaoAberta)
        {
            throw new RuntimeException("Já existe uma sessão ativa para esta mesa.");
        }

        MesaSessao mesaSessao = new MesaSessao();
        mesaSessao.setMesa(mesa);
        mesaSessao.setQuantidadePessoas(MesaSessaoRequestDTO.getQuantidadePessoas());
        mesaSessao.setStatus(MesaSessaoRequestDTO.getStatus());
        mesaSessao.setAtendenteResponsavel(atendenteResponsavel);
        mesaSessao.setCliente(cliente);

        return toResponseDTO(mesaSessaoRepository.save(mesaSessao));
    }

    public MesaSessaoResponseDTO buscarPorMesaId(Long numeroMesa)
    {
        MesaSessao mesaSessao = mesaSessaoRepository
                .findByMesaIdAndStatus(numeroMesa, StatusMesa.OCUPADA)
                .orElseThrow(() -> new RuntimeException("Nenhuma sessão OCUPADA encontrada para esta mesa."));

        return toResponseDTO(mesaSessao);
    }

    // Read
    public MesaSessaoResponseDTO obterPorId(Long id)
    {
        MesaSessao mesa = mesaSessaoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Mesa não encontrada"));
        return toResponseDTO(mesa);
    }

    // Read todas as mesas
    // public List<MesaSessaoResponseDTO> listarMesasAbertas()
    // {
    //     List<MesaSessao> mesas = mesaSessaoRepository.findByStatusIn(List.of(StatusMesa.OCUPADA, StatusMesa.FECHAMENTO));

    //     return mesas.stream().map(this::toResponseDTO).toList();
    // }

    public List<Map<String, Object>> listarMesasAbertas()
    {
        List<MesaSessao> mesas = mesaSessaoRepository.findByStatusIn(List.of(StatusMesa.OCUPADA, StatusMesa.FECHAMENTO));

        return mesas.stream().map(mesa ->
        {
            Map<String, Object> mapa = new HashMap<>();

            mapa.put("status", mesa.getStatus());
            mapa.put("numeroMesa", mesa.getMesa().getId());

            String nomeCliente = null;
            if (mesa.getCliente() != null)
            {
                nomeCliente = mesa.getCliente().getNome();
            }

            mapa.put("nomeCliente", nomeCliente);

                return mapa;
        }).toList();
    }

    // Update
    public MesaSessaoResponseDTO atualizar(Long id, @Valid MesaSessaoRequestDTO MesaSessaoRequestDTO)
    {
        MesaSessao mesaExistente = mesaSessaoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Mesa não encontrada"));

        Usuario atendenteResponsavel = null;
        if (MesaSessaoRequestDTO.getIdAtendenteResponsavel() != null)
        {
            atendenteResponsavel = usuarioRepository.findById(MesaSessaoRequestDTO.getIdAtendenteResponsavel())
            .orElseThrow(() -> new RuntimeException("Atendente não encontrado"));
        }
        
        Cliente cliente = null;

        if (MesaSessaoRequestDTO.getIdCliente() != null)
        {
            cliente = clienteRepository.findById(MesaSessaoRequestDTO.getIdCliente()).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        }

        BigDecimal valorTotalMesa = pedidoService.listarPedidosDaMesa(id)
        .stream()
        .map(PedidoResumoDTO::getValorTotal)
        .reduce(BigDecimal.ZERO, BigDecimal::add);

        mesaExistente.setValorTotalMesa(valorTotalMesa); // A jamanta esqueceu de colocar pra enviar o valor total da mesa ao atualizar

        BigDecimal taxaServico;

        if (MesaSessaoRequestDTO.getTaxaServico() == null)
        {
            taxaServico = valorTotalMesa.multiply(new BigDecimal("0.10")); // Alterar para uma configuração que possa ser alterada pelo usuário no futuro
            mesaExistente.setValorTotalMesaServico(valorTotalMesa.add(taxaServico));
        } else
        {
            taxaServico = MesaSessaoRequestDTO.getTaxaServico();
        }

        mesaExistente.setTaxaServico(taxaServico);

        // mesaExistente.setValorTotalMesaServico(valorTotalMesa.add(taxaServico));


        if (MesaSessaoRequestDTO.getStatus() == StatusMesa.FECHADA)
        {
            // // Calcular total da mesa
            // BigDecimal valorTotalMesa = pedidoService.listarPedidosDaMesa(id)
            // .stream()
            // .map(PedidoResumoDTO::getValorTotal)
            // .reduce(BigDecimal.ZERO, BigDecimal::add);

            // BigDecimal taxaServico;

            // if (MesaSessaoRequestDTO.getTaxaServico() == null)
            // {
            //     taxaServico = valorTotalMesa.multiply(new BigDecimal("0.10")); // Alterar para uma configuração que possa ser alterada pelo usuário no futuro
            // } else
            // {
            //     taxaServico = MesaSessaoRequestDTO.getTaxaServico();
            // }

            // Salvar taxa na mesa
            mesaExistente.setTaxaServico(taxaServico);

            // // Calcular total com serviço
            // BigDecimal valorTotalMesaServico = valorTotalMesa.add(taxaServico);

            // Calcular pagamentos já realizados
            BigDecimal totalPagamentos = pagamentoService.listarPagamentosPorMesa(id)
                .stream()
                .map(PagamentoResponseDTO::getValorPagamento)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

            // Validar pagamento mínimo
            if (totalPagamentos.compareTo(valorTotalMesa) < 0)
            {
                BigDecimal falta = valorTotalMesa.subtract(totalPagamentos);
                throw new RuntimeException("Não é possível fechar a mesa. Ainda faltam R$ " +  String.format("%.2f", falta));
            }

            // Calcula o quanto sobra após pagar a mesa
            BigDecimal sobressalente = totalPagamentos.subtract(valorTotalMesa);

            // Se não pagou nada da taxa
            if (sobressalente.compareTo(BigDecimal.ZERO) == 0)
            {
                // Taxa não paga
                mesaExistente.setTaxaServico(taxaServico);
                mesaExistente.setValorTotalMesaServico(valorTotalMesa.add(taxaServico));
            }

            // Se pagou parte da taxa
            else if (sobressalente.compareTo(taxaServico) < 0)
            {
                // Pagou parcialmente
                mesaExistente.setTaxaServico(taxaServico); // taxa inteira prevista
                mesaExistente.setValorTotalMesaServico(valorTotalMesa.add(taxaServico));
            }

            // Se pagou exatamente a taxa
            else if (sobressalente.compareTo(taxaServico) == 0)
            {
                mesaExistente.setTaxaServico(taxaServico);
                mesaExistente.setValorTotalMesaServico(valorTotalMesa.add(taxaServico));
            }

            // Se pagou mais do que a taxa (excedente)
            else
            {
                // Quanto excedeu após pagar taxa
                BigDecimal excedente = sobressalente.subtract(taxaServico);

                // soma o excedente como gorjeta adicional
                BigDecimal novaTaxa = taxaServico.add(excedente);

                mesaExistente.setTaxaServico(novaTaxa);
                mesaExistente.setValorTotalMesaServico(valorTotalMesa.add(novaTaxa));
            }

            mesaExistente.setHorarioFechamento(java.time.LocalDateTime.now());
            mesaExistente.setValorTotalMesa(valorTotalMesa);
            // mesaExistente.setValorTotalMesaServico(valorTotalMesaServico);
        }
        // // Se o usuário não enviou taxa, calcula 10%
        // if (MesaSessaoRequestDTO.getTaxaServico() == null)
        // {
        //     BigDecimal taxaServico = valorTotalMesa.multiply(new BigDecimal("0.10"));
        // }

        Mesa mesa = mesaRepository.findById(MesaSessaoRequestDTO.getNumeroMesa()).orElseThrow(() -> new RuntimeException("Mesa não encontrada"));

        // List<StatusMesa> statusAbertos = Arrays.asList(StatusMesa.OCUPADA, StatusMesa.FECHAMENTO);

        // boolean existeSessaoAberta = mesaSessaoRepository.existsByMesaIdAndStatusIn(MesaSessaoRequestDTO.getNumeroMesa(), statusAbertos);

        // if (existeSessaoAberta)
        // {
        //     throw new RuntimeException("Já existe uma sessão ativa para esta mesa.");
        // } 
        
        mesaExistente.setMesa(mesa);
        mesaExistente.setQuantidadePessoas(MesaSessaoRequestDTO.getQuantidadePessoas());
        mesaExistente.setStatus(MesaSessaoRequestDTO.getStatus());
        mesaExistente.setAtendenteResponsavel(atendenteResponsavel);
        mesaExistente.setCliente(cliente);
        // mesaExistente.setTaxaServico(MesaSessaoRequestDTO.getTaxaServico());
        return toResponseDTO(mesaSessaoRepository.save(mesaExistente));
    }

    // Delete
    public void deletar(Long id)
    {
        MesaSessao mesaExistente = mesaSessaoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Mesa não encontrada"));
        mesaSessaoRepository.delete(mesaExistente);
    }

    // Método auxiliar para converter Mesa em MesaResponseDTO
    public MesaSessaoResponseDTO toResponseDTO(MesaSessao mesaSessao)
    {
        List<PedidoResumoDTO> pedidosDTO = pedidoService.listarPedidosDaMesa(mesaSessao.getId());
        List<PagamentoResponseDTO> pagamentosDTO = pagamentoService.listarPagamentosPorMesa(mesaSessao.getId());
        
        // Calcula o total dos pedidos
        // BigDecimal valorTotalMesa = pedidosDTO.stream().map(PedidoResumoDTO::getValorTotal)
        // .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal valorTotalMesaServico = mesaSessao.getValorTotalMesaServico();

        // Monta o DTO final
        MesaSessaoResponseDTO MesaSessaoResponseDTO = new MesaSessaoResponseDTO();
        MesaSessaoResponseDTO.setId(mesaSessao.getId());
        MesaSessaoResponseDTO.setAtendenteAbertura(mesaSessao.getAtendenteAbertura() != null ? mesaSessao.getAtendenteAbertura().getNome() : null);
        MesaSessaoResponseDTO.setNomeAtendenteResponsavel(mesaSessao.getAtendenteResponsavel() != null ? mesaSessao.getAtendenteResponsavel().getNome() : null);
        MesaSessaoResponseDTO.setNumeroMesa(mesaSessao.getMesa().getId());
        MesaSessaoResponseDTO.setQuantidadePessoas(mesaSessao.getQuantidadePessoas());
        MesaSessaoResponseDTO.setNomeCliente(mesaSessao.getCliente() != null ? mesaSessao.getCliente().getNome() : null);
        MesaSessaoResponseDTO.setStatus(mesaSessao.getStatus());
        MesaSessaoResponseDTO.setHorarioAbertura(mesaSessao.getHorarioAbertura());
        MesaSessaoResponseDTO.setHorarioFechamento(mesaSessao.getHorarioFechamento());
        MesaSessaoResponseDTO.setPedidos(pedidosDTO);
        MesaSessaoResponseDTO.setPagamentos(pagamentosDTO);
        MesaSessaoResponseDTO.setValorTotalMesa(mesaSessao.getValorTotalMesa());
        MesaSessaoResponseDTO.setTaxaServico(mesaSessao.getTaxaServico());
        MesaSessaoResponseDTO.setValorTotalMesaServico(valorTotalMesaServico);

        return MesaSessaoResponseDTO;
    }
    
}
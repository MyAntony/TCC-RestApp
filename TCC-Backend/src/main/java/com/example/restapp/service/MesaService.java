package com.example.restapp.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.restapp.dto.MesaDTO.MesaRequestDTO;
import com.example.restapp.dto.MesaDTO.MesaResponseDTO;
import com.example.restapp.dto.PedidoDTO.PedidoResumoDTO;
import com.example.restapp.model.Usuario;
import com.example.restapp.model.principal.Cliente;
import com.example.restapp.model.principal.Mesa;
import com.example.restapp.repository.ClienteRepository;
import com.example.restapp.repository.MesaRepository;
import com.example.restapp.repository.UsuarioRepository;

@Service
public class MesaService
{
    
    @Autowired
    private MesaRepository mesaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PedidoService pedidoService;


    // Create
    public MesaResponseDTO salvar(MesaRequestDTO mesaRequestDTO)
    {
        Usuario atendenteResponsavel = null;
        if (mesaRequestDTO.getIdAtendenteResponsavel() != null)
        {
            atendenteResponsavel = usuarioRepository.findById(mesaRequestDTO.getIdAtendenteResponsavel())
            .orElseThrow(() -> new RuntimeException("Atendente não encontrado"));
        }

        Cliente cliente = null;
        if (mesaRequestDTO.getIdCliente() != null)
        {
            cliente = clienteRepository.findById(mesaRequestDTO.getIdCliente()).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        }

        Mesa mesa = new Mesa();
        mesa.setNumeroMesa(mesaRequestDTO.getNumeroMesa());
        mesa.setQuantidadePessoas(mesaRequestDTO.getQuantidadePessoas());
        mesa.setStatus(mesaRequestDTO.getStatus());
        mesa.setAtendenteResponsavel(atendenteResponsavel);
        mesa.setCliente(cliente);

        return toResponseDTO(mesaRepository.save(mesa));
    }

    // Read
    public MesaResponseDTO obterPorId(Long id)
    {
        Mesa mesa = mesaRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Mesa não encontrada"));
        return toResponseDTO(mesa);
    }

    // Update
    public MesaResponseDTO atualizar(Long id, MesaRequestDTO mesaRequestDTO)
    {
        Mesa mesaExistente = mesaRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Mesa não encontrada"));

        Usuario atendenteResponsavel = null;
        if (mesaRequestDTO.getIdAtendenteResponsavel() != null)
        {
            atendenteResponsavel = usuarioRepository.findById(mesaRequestDTO.getIdAtendenteResponsavel())
            .orElseThrow(() -> new RuntimeException("Atendente não encontrado"));
        }
        
        Cliente cliente = null;
        if (mesaRequestDTO.getIdCliente() != null)
        {
            cliente = clienteRepository.findById(mesaRequestDTO.getIdCliente()).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        }
        
        mesaExistente.setNumeroMesa(mesaRequestDTO.getNumeroMesa());
        mesaExistente.setQuantidadePessoas(mesaRequestDTO.getQuantidadePessoas());
        mesaExistente.setStatus(mesaRequestDTO.getStatus());
        mesaExistente.setAtendenteResponsavel(atendenteResponsavel);
        mesaExistente.setCliente(cliente);
        return toResponseDTO(mesaRepository.save(mesaExistente));
    }

    // Delete
    public void deletar(Long id)
    {
        Mesa mesaExistente = mesaRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Mesa não encontrada"));
        mesaRepository.delete(mesaExistente);
    }

    // Método auxiliar para converter Mesa em MesaResponseDTO
    private MesaResponseDTO toResponseDTO(Mesa mesa)
    {
        List<PedidoResumoDTO> pedidosDTO = pedidoService.listarPedidosDaMesa(mesa.getId());

        // Converte os pedidos da mesa para resumos
        // List<PedidoResumoDTO> pedidosDTO;

        // if (mesa.getPedidos() != null)
        // {
        //     pedidosDTO = mesa.getPedidos().stream()
        //         .map(pedido -> new PedidoResumoDTO
        //         (
        //             pedido.getProduto().getNomeProduto(),
        //             pedido.getQuantidadeProduto(),
        //             pedido.getValorUnitario(),
        //             pedido.getValorTotal()
        //         )).toList();
        // } else
        // {
        //     pedidosDTO = new ArrayList<>(); // lista vazia caso não haja pedidos
        // }
        
        // Calcula o valor total da mesa
        double valorTotalMesa = pedidosDTO.stream()
            .mapToDouble(PedidoResumoDTO::getValorTotal)
            .sum();

        // Adiciona taxa de serviço (por exemplo, 10%)
        double valorTotalMesaServico = valorTotalMesa * 1.10;

        // Monta o DTO final
        MesaResponseDTO mesaResponseDTO = new MesaResponseDTO();
        mesaResponseDTO.setId(mesa.getId());
        mesaResponseDTO.setAtendenteAbertura(mesa.getAtendenteAbertura() != null ? mesa.getAtendenteAbertura().getNome() : null);
        mesaResponseDTO.setNomeAtendenteResponsavel(mesa.getAtendenteResponsavel() != null ? mesa.getAtendenteResponsavel().getNome() : null);
        mesaResponseDTO.setNumeroMesa(mesa.getNumeroMesa());
        mesaResponseDTO.setQuantidadePessoas(mesa.getQuantidadePessoas());
        mesaResponseDTO.setNomeCliente(mesa.getCliente() != null ? mesa.getCliente().getNome() : null);
        mesaResponseDTO.setStatus(mesa.getStatus());
        mesaResponseDTO.setHorarioAbertura(mesa.getHorarioAbertura());
        mesaResponseDTO.setHorarioFechamento(mesa.getHorarioFechamento());
        mesaResponseDTO.setPedidos(pedidosDTO);
        mesaResponseDTO.setValorTotalMesa(valorTotalMesa);
        mesaResponseDTO.setValorTotalMesaServico(valorTotalMesaServico);

        return mesaResponseDTO;
    }
    
}
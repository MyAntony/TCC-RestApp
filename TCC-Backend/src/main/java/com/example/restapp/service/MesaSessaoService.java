package com.example.restapp.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restapp.dto.MesaSessaoDTO.MesaSessaoRequestDTO;
import com.example.restapp.dto.MesaSessaoDTO.MesaSessaoResponseDTO;
import com.example.restapp.dto.PedidoDTO.PedidoResumoDTO;
import com.example.restapp.model.Usuario;
import com.example.restapp.model.enums.StatusMesa;
import com.example.restapp.model.principal.Cliente;
import com.example.restapp.model.principal.Mesa;
import com.example.restapp.model.principal.MesaSessao;
import com.example.restapp.repository.ClienteRepository;
import com.example.restapp.repository.MesaSessaoRepository;
import com.example.restapp.repository.UsuarioRepository;
import com.example.restapp.repository.MesaRepository;

@Service
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


    // Create
    public MesaSessaoResponseDTO salvar(MesaSessaoRequestDTO MesaSessaoRequestDTO)
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

        MesaSessao mesaSessao = new MesaSessao();
        mesaSessao.setMesa(mesa);
        mesaSessao.setQuantidadePessoas(MesaSessaoRequestDTO.getQuantidadePessoas());
        mesaSessao.setStatus(MesaSessaoRequestDTO.getStatus());
        mesaSessao.setAtendenteResponsavel(atendenteResponsavel);
        mesaSessao.setCliente(cliente);

        return toResponseDTO(mesaSessaoRepository.save(mesaSessao));
    }

    // Read
    public MesaSessaoResponseDTO obterPorId(Long id)
    {
        MesaSessao mesa = mesaSessaoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Mesa não encontrada"));
        return toResponseDTO(mesa);
    }

    // Read todas as mesas
    public List<MesaSessaoResponseDTO> listarMesasAbertas()
    {
        List<MesaSessao> mesas = mesaSessaoRepository.findByStatusIn(List.of(StatusMesa.OCUPADA, StatusMesa.FECHAMENTO));

        return mesas.stream().map(this::toResponseDTO).toList();
    }


    // Update
    public MesaSessaoResponseDTO atualizar(Long id, MesaSessaoRequestDTO MesaSessaoRequestDTO)
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

        Mesa mesa = mesaRepository.findById(MesaSessaoRequestDTO.getNumeroMesa()).orElseThrow(() -> new RuntimeException("Mesa não encontrada"));
        
        mesaExistente.setMesa(mesa);
        mesaExistente.setQuantidadePessoas(MesaSessaoRequestDTO.getQuantidadePessoas());
        mesaExistente.setStatus(MesaSessaoRequestDTO.getStatus());
        mesaExistente.setAtendenteResponsavel(atendenteResponsavel);
        mesaExistente.setCliente(cliente);
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
    private MesaSessaoResponseDTO toResponseDTO(MesaSessao mesaSessao)
    {
        List<PedidoResumoDTO> pedidosDTO = pedidoService.listarPedidosDaMesa(mesaSessao.getId());

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
        MesaSessaoResponseDTO.setValorTotalMesa(valorTotalMesa);
        MesaSessaoResponseDTO.setValorTotalMesaServico(valorTotalMesaServico);

        return MesaSessaoResponseDTO;
    }
    
}
package com.example.restapp.service;

import com.example.restapp.dto.PedidoDTO.*;
import com.example.restapp.model.principal.Pedido;
import com.example.restapp.model.produtos.Produto;
import com.example.restapp.repository.PedidoRepository;
import com.example.restapp.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class PedidoService
{
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    // Create
    public PedidoResponseDTO salvar(@Valid PedidoRequestDTO pedidoRequestDTO)
    {
        Produto produto = produtoRepository.findById(pedidoRequestDTO.getIdProduto())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        Pedido pedido = new Pedido();
        pedido.setMesa(pedidoRequestDTO.getMesa());
        pedido.setProduto(produto);
        pedido.setDescricaoPedido(pedidoRequestDTO.getDescricaoPedido());
        pedido.setQuantidadeProduto(pedidoRequestDTO.getQuantidadeProduto()!= null ? pedidoRequestDTO.getQuantidadeProduto() : 1);
        pedido.setValorUnitario(produto.getPrecoVenda());
        pedido.setValorTotal(pedido.getValorUnitario() * pedido.getQuantidadeProduto());

        return toResponseDTO(pedidoRepository.save(pedido));

    }

    // Read
    public PedidoResponseDTO buscarPorId(Long id)
    {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        return toResponseDTO(pedido);
    }

    // Read All
    public java.util.List<PedidoResponseDTO> listarTodos()
    {
        return pedidoRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    // Update
    public PedidoResponseDTO atualizar(@Valid PedidoRequestDTO pedidoRequestDTO, Long id)
    {
        Pedido pedidoAtualizar = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        Produto produto = produtoRepository.findById(pedidoRequestDTO.getIdProduto())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        pedidoAtualizar.setMesa(pedidoRequestDTO.getMesa());
        pedidoAtualizar.setProduto(produto);
        pedidoAtualizar.setDescricaoPedido(pedidoRequestDTO.getDescricaoPedido());
        pedidoAtualizar.setQuantidadeProduto(pedidoRequestDTO.getQuantidadeProduto() != null ? pedidoRequestDTO.getQuantidadeProduto() : pedidoAtualizar.getQuantidadeProduto());
        pedidoAtualizar.setValorUnitario(produto.getPrecoVenda());
        pedidoAtualizar.setValorTotal(pedidoAtualizar.getValorUnitario() * pedidoAtualizar.getQuantidadeProduto());

        return toResponseDTO(pedidoRepository.save(pedidoAtualizar));
    }

    // Delete
    public void excluir(Long id)
    {
        Pedido pedidoExcluir = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado."));

        pedidoRepository.deleteById(pedidoExcluir.getId());
    }

    private PedidoResponseDTO toResponseDTO(Pedido pedido)
    {
        PedidoResponseDTO pedidoResponseDTO = new PedidoResponseDTO();
        pedidoResponseDTO.setId(pedido.getId());
        pedidoResponseDTO.setMesa(pedido.getMesa());
        pedidoResponseDTO.setNomeProduto(pedido.getProduto().getNomeProduto());
        pedidoResponseDTO.setDescricaoPedido(pedido.getDescricaoPedido());
        pedidoResponseDTO.setQuantidadeProduto(pedido.getQuantidadeProduto());
        pedidoResponseDTO.setValorUnitario(pedido.getValorUnitario());
        pedidoResponseDTO.setValorTotal(pedido.getValorTotal());
        pedidoResponseDTO.setHorarioLancamento(pedido.getHorarioLancamento());
        pedidoResponseDTO.setNomeUsuario(pedido.getUsuario().getNome());
        return pedidoResponseDTO;
    }

}
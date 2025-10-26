package com.example.restapp.service;

import com.example.restapp.dto.ProdutoDTO.ProdutoRequestDTO;
import com.example.restapp.dto.ProdutoDTO.ProdutoResponseDTO;
import com.example.restapp.model.produtos.CategoriaProdutos;
import com.example.restapp.model.produtos.Produto;
import com.example.restapp.repository.CategoriaProdutosRepository;
import com.example.restapp.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
public class ProdutoService
{
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaProdutosRepository categoriaProdutosRepository;


    public ProdutoService(ProdutoRepository produtoRepository)
    {
        this.produtoRepository = produtoRepository;
    }

    // Create
    public ProdutoResponseDTO salvar(@Valid ProdutoRequestDTO produtoRequestDTO)
    {
        CategoriaProdutos categoriaProdutos = categoriaProdutosRepository.findById(produtoRequestDTO.getIdCategoriaProdutos())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        Produto produto = new Produto();
        produto.setNomeProduto(produtoRequestDTO.getNomeProduto());
        produto.setCategoriaProdutos(categoriaProdutos);
        produto.setPrecoCusto(produtoRequestDTO.getPrecoCusto());
        produto.setPrecoVenda(produtoRequestDTO.getPrecoVenda());
        produto.setDescricao(produtoRequestDTO.getDescricao());
        produto.setImagem(produtoRequestDTO.getImagem());
        
        produtoRepository.save(produto);
        return toResponseDTO(produto);
    }

    // Read
    public List<ProdutoResponseDTO> listarTodos()
    {
        return produtoRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    // Read com filtro
    public List<ProdutoResponseDTO> buscarPorNome(String nomeProduto)
    {
        return produtoRepository.findByNomeProdutoContainingIgnoreCase(nomeProduto)
            .stream()
            .map(produto -> new ProdutoResponseDTO(produto.getId(), produto.getNomeProduto(), produto.getCategoriaProdutos().getNomeCategoriaProdutos(),
                    produto.getPrecoCusto(), produto.getPrecoVenda(), produto.getDescricao(), produto.getImagem()))
            .collect(Collectors.toList());
    }

    // Update
    public ProdutoResponseDTO atualizar(Long id, @Valid ProdutoRequestDTO produtoRequestDTO)
    {
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Produto não encontrado."));

        CategoriaProdutos categoriaProdutos = categoriaProdutosRepository.findById(produtoRequestDTO.getIdCategoriaProdutos())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        produto.setNomeProduto(produtoRequestDTO.getNomeProduto());
        produto.setCategoriaProdutos(categoriaProdutos);
        produto.setPrecoCusto(produtoRequestDTO.getPrecoCusto());
        produto.setPrecoVenda(produtoRequestDTO.getPrecoVenda());
        produto.setDescricao(produtoRequestDTO.getDescricao());
        produto.setImagem(produtoRequestDTO.getImagem());
        
        produtoRepository.save(produto);
        return toResponseDTO(produto);
    }

    // Delete
    public void excluir(Long id)
    {
        Produto produtoExcluir = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado."));
        // Verifica se o produto está em estoque antes de excluir

        produtoRepository.deleteById(produtoExcluir.getId());
    }

    // Método auxiliar para converter Produto em ProdutoResponseDTO
    private ProdutoResponseDTO toResponseDTO(Produto produto)
    {
        return new ProdutoResponseDTO(
                produto.getId(),
                produto.getNomeProduto(),
                produto.getCategoriaProdutos().getNomeCategoriaProdutos(),
                produto.getPrecoCusto(),
                produto.getPrecoVenda(),
                produto.getDescricao(),
                produto.getImagem()
        );
    }

}

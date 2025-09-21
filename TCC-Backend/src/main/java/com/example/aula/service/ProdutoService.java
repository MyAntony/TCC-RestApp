package com.example.aula.service;

// import com.example.aula.exception.EmailJaCadastradoException;
import com.example.aula.model.Produto;
import com.example.aula.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class ProdutoService
{
    private ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository)
    {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> listarTodos()
    {
        return produtoRepository.findAll();
    }

    public Produto salvar(@Valid Produto produto)
    {
        return produtoRepository.save(produto);
    }

    public Produto atualizar(Long id, @Valid Produto produto)
    {
        Produto produtoAtualizar = produtoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Produto não encontrado."));

        produtoAtualizar.setNome(produto.getNome());
        produtoAtualizar.setDescricao(produto.getDescricao());
        produtoAtualizar.setPreco(produto.getPreco());
        produtoAtualizar.setCategoria(produto.getCategoria());
        produtoAtualizar.setUrl(produto.getUrl());
        produtoAtualizar.setDisponibilidade(produto.getDisponibilidade());
        
        

        return produtoRepository.save(produtoAtualizar);
    }

    public void excluir(Long id)
    {
        Produto produtoExcluir = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("pedido não encontrado."));
        // Verifica se o produto está em estoque antes de excluir

        produtoRepository.deleteById(produtoExcluir.getId());
    }

}

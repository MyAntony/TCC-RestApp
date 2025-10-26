package com.example.restapp.service;

import com.example.restapp.dto.CategoriaProdutosDTO.CategoriaProdutosRequestDTO;
import com.example.restapp.dto.CategoriaProdutosDTO.CategoriaProdutosResponseDTO;
import com.example.restapp.model.produtos.CategoriaProdutos;
import com.example.restapp.repository.CategoriaProdutosRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import jakarta.validation.Valid;

@Service 
public class CategoriaProdutosService
{
    private CategoriaProdutosRepository categoriaProdutosRepository;

    public CategoriaProdutosService(CategoriaProdutosRepository categoriaProdutosRepository)
    {
        this.categoriaProdutosRepository = categoriaProdutosRepository;
    }

    // Create
    public CategoriaProdutosResponseDTO salvar(@Valid CategoriaProdutosRequestDTO categoriaProdutosRequestDTO)
    {
        CategoriaProdutos categoriaProdutos = new CategoriaProdutos();
        categoriaProdutos.setNomeCategoriaProdutos(categoriaProdutosRequestDTO.getNomeCategoriaProdutos());
        categoriaProdutos.setDescricaoCategoriaProdutos(categoriaProdutosRequestDTO.getDescricaoCategoriaProdutos());
        
        categoriaProdutosRepository.save(categoriaProdutos);
        return toResponseDTO(categoriaProdutos);
    }

    // Read
    public List<CategoriaProdutosResponseDTO> listarTodos()
    {
        return categoriaProdutosRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    // Read com filtro
    public List<CategoriaProdutosResponseDTO> buscarPorNome(String nomeCategoriaProdutos)
    {
        return categoriaProdutosRepository.findByNomeCategoriaProdutosContainingIgnoreCase(nomeCategoriaProdutos)
            .stream()
            .map(categoriaProdutos -> new CategoriaProdutosResponseDTO(
                categoriaProdutos.getId(),
                categoriaProdutos.getNomeCategoriaProdutos(),
                categoriaProdutos.getDescricaoCategoriaProdutos(),
                categoriaProdutos.getDataCriacao()
            ))
            .collect(Collectors.toList());
    }

    // Update
    public CategoriaProdutosResponseDTO atualizar(Long id, @Valid CategoriaProdutosRequestDTO dto)
    {
        CategoriaProdutos categoriaProdutos = categoriaProdutosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria de produtos não encontrada."));

        categoriaProdutos.setNomeCategoriaProdutos(dto.getNomeCategoriaProdutos());
        categoriaProdutos.setDescricaoCategoriaProdutos(dto.getDescricaoCategoriaProdutos());

        categoriaProdutosRepository.save(categoriaProdutos);

        return toResponseDTO(categoriaProdutos);
    }

    // Delete
    public void excluir(Long id)
    {
        CategoriaProdutos categoriaProdutos = categoriaProdutosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria de produtos não encontrada."));

        // Verifica se existem produtos associados antes de deletar
        if (categoriaProdutos.getProdutos() != null && !categoriaProdutos.getProdutos().isEmpty())
        {
            throw new RuntimeException("Não é possível excluir a categoria de produtos porque existem produtos associados a ela.");
        }

        categoriaProdutosRepository.delete(categoriaProdutos);
    }

    // Conversor auxiliar (obrigatório para mapear entidade -> DTO)
    private CategoriaProdutosResponseDTO toResponseDTO(CategoriaProdutos categoriaProdutos)
    {
        return new CategoriaProdutosResponseDTO(
            categoriaProdutos.getId(),
            categoriaProdutos.getNomeCategoriaProdutos(),
            categoriaProdutos.getDescricaoCategoriaProdutos(),
            categoriaProdutos.getDataCriacao()
        );
    }
}

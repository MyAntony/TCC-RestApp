package com.example.aula.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.example.aula.dto.CategoriaContasDTO.CategoriaContasRequestDTO;
import com.example.aula.dto.CategoriaContasDTO.CategoriaContasResponseDTO;
import com.example.aula.dto.FornecedorDTO.FornecedorResponseDTO;
import com.example.aula.model.financeiro.CategoriaContas;
import com.example.aula.repository.CategoriaContasRepository;
import jakarta.validation.Valid;

@Service
public class CategoriaContasService
{
    private CategoriaContasRepository categoriaContasRepository;

    public CategoriaContasService(CategoriaContasRepository categoriaContasRepository)
    {
        this.categoriaContasRepository = categoriaContasRepository;
    }

    // Create
    public CategoriaContasResponseDTO salvar(@Valid CategoriaContasRequestDTO dto)
    {
        CategoriaContas conta = new CategoriaContas();
        conta.setNomeCategoria(dto.getNomeCategoria());
        conta.setDescricaoCategoria(dto.getDescricaoCategoria());

        categoriaContasRepository.save(conta);

        return toResponseDTO(conta);
    }

    // Read
    public List<CategoriaContasResponseDTO> listarTodos()
    {
        return categoriaContasRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    // Read com filtro
    public List<CategoriaContasResponseDTO> buscarPorNome(String nomeCategoria)
    {
        return categoriaContasRepository.findByNomeCategoriaContainingIgnoreCase(nomeCategoria)
            .stream()
            .map(categoria -> new CategoriaContasResponseDTO(categoria.getId(), categoria.getNomeCategoria(), categoria.getDescricaoCategoria()))
            .collect(Collectors.toList());
    }


    // Update
    public CategoriaContasResponseDTO atualizar(Long id, @Valid CategoriaContasRequestDTO dto)
    {
        CategoriaContas conta = categoriaContasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta a pagar não encontrada."));

        conta.setNomeCategoria(dto.getNomeCategoria());
        conta.setDescricaoCategoria(dto.getDescricaoCategoria());

        categoriaContasRepository.save(conta);

        return toResponseDTO(conta);
    }

    // Delete
    public void excluir(Long id)
    {
        CategoriaContas conta = categoriaContasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta a pagar não encontrada."));
        categoriaContasRepository.delete(conta);
    }

    // Conversor auxiliar (obrigatório para mapear entidade -> DTO)
    private CategoriaContasResponseDTO toResponseDTO(CategoriaContas conta)
    {
        return new CategoriaContasResponseDTO(
                conta.getId(),
                conta.getNomeCategoria(),
                conta.getDescricaoCategoria()
        );
    }
}

package com.example.aula.service;

import com.example.aula.dto.FornecedorDTO.FornecedorRequestDTO;
import com.example.aula.dto.FornecedorDTO.FornecedorResponseDTO;
import com.example.aula.model.financeiro.Fornecedor;
import com.example.aula.repository.FornecedorRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
public class FornecedorService
{
    private FornecedorRepository fornecedorRepository;

    public FornecedorService(FornecedorRepository fornecedorRepository)
    {
        this.fornecedorRepository = fornecedorRepository;
    }

    // Create
    public Fornecedor salvar(@Valid FornecedorRequestDTO fornecedorRequestDTO)
    {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNomeFantasia(fornecedorRequestDTO.getNomeFantasia());
        fornecedor.setRazaoSocial(fornecedorRequestDTO.getRazaoSocial());
        fornecedor.setTipoDocumento(fornecedorRequestDTO.getTipoDocumento());
        fornecedor.setCpf(fornecedorRequestDTO.getCpf());
        fornecedor.setCnpj(fornecedorRequestDTO.getCnpj());
        fornecedor.setTelefone(fornecedorRequestDTO.getTelefone());
        fornecedor.setEmail(fornecedorRequestDTO.getEmail());
        fornecedor.setEndereco(fornecedorRequestDTO.getEndereco());

        return fornecedorRepository.save(fornecedor);
    }

    // Read
    public List<FornecedorResponseDTO> listarTodos()
    {
        return fornecedorRepository.findAll()
                .stream()
                .map(fornecedor -> new FornecedorResponseDTO(
                        fornecedor.getNomeFantasia()
                ))
                .collect(Collectors.toList());
    }

    // Read com filtro
    public List<FornecedorResponseDTO> buscarPorNome(String nomeFantasia)
    {
        return fornecedorRepository.findByNomeFantasiaContainingIgnoreCase(nomeFantasia)
            .stream()
            .map(fornecedor -> new FornecedorResponseDTO(fornecedor.getNomeFantasia()))
            .collect(Collectors.toList());
    }

    // Update
    public Fornecedor atualizar(@Valid FornecedorRequestDTO fornecedorRequestDTO)
    {
        Fornecedor fornecedorAtualizar = fornecedorRepository.findById(fornecedorRequestDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Fornecedor não encontrado."));

        fornecedorAtualizar.setNomeFantasia(fornecedorRequestDTO.getNomeFantasia());
        fornecedorAtualizar.setRazaoSocial(fornecedorRequestDTO.getRazaoSocial());
        fornecedorAtualizar.setTipoDocumento(fornecedorRequestDTO.getTipoDocumento());
        fornecedorAtualizar.setCpf(fornecedorRequestDTO.getCpf());
        fornecedorAtualizar.setCnpj(fornecedorRequestDTO.getCnpj());
        fornecedorAtualizar.setTelefone(fornecedorRequestDTO.getTelefone());
        fornecedorAtualizar.setEmail(fornecedorRequestDTO.getEmail());
        fornecedorAtualizar.setEndereco(fornecedorRequestDTO.getEndereco());

        return fornecedorRepository.save(fornecedorAtualizar);
    }

    // Delete
    public void excluir(Long id)
    {
        Fornecedor fornecedorExcluir = fornecedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado."));
        // Verifica se o fornecedor está em estoque antes de excluir
        
        fornecedorRepository.delete(fornecedorExcluir);
        // fornecedorRepository.deleteById(fornecedorExcluir.getId());
    }

}

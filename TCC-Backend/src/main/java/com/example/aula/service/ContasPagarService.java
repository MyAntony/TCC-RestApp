package com.example.aula.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.aula.dto.ContasPagarDTO.ContasPagarRequestDTO;
import com.example.aula.dto.ContasPagarDTO.ContasPagarResponseDTO;
import com.example.aula.model.financeiro.StatusPagamento;
import com.example.aula.model.financeiro.CategoriaContas;
import com.example.aula.model.financeiro.Fornecedor;
import com.example.aula.model.financeiro.ContasPagar;
import com.example.aula.repository.CategoriaContasRepository;
import com.example.aula.repository.FornecedorRepository;
import com.example.aula.repository.ContasPagarRepository;
import jakarta.validation.Valid;

@Service
public class ContasPagarService
{

    @Autowired
    private ContasPagarRepository contasPagarRepository;

    @Autowired
    private CategoriaContasRepository categoriaRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    // Create
    public ContasPagarResponseDTO salvar(@Valid ContasPagarRequestDTO dto)
    {
        CategoriaContas categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        Fornecedor fornecedor = fornecedorRepository.findById(dto.getFornecedorId())
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));

        ContasPagar conta = new ContasPagar();
        conta.setCategoria(categoria);
        conta.setFornecedor(fornecedor);
        conta.setDescricao(dto.getDescricao());
        conta.setDataVencimento(dto.getDataVencimento());
        conta.setValor(dto.getValor());
        conta.setDataPagamento(dto.getDataPagamento());
        conta.setStatusPagamento(StatusPagamento.valueOf(dto.getStatusPagamento()));

        contasPagarRepository.save(conta);

        return toResponseDTO(conta);
    }

    // Read
    public List<ContasPagarResponseDTO> listarTodos()
    {
        return contasPagarRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    // Update
    public ContasPagarResponseDTO atualizar(Long id, @Valid ContasPagarRequestDTO dto)
    {
        ContasPagar conta = contasPagarRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta a pagar não encontrada."));

        CategoriaContas categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        Fornecedor fornecedor = fornecedorRepository.findById(dto.getFornecedorId())
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));

        conta.setCategoria(categoria);
        conta.setFornecedor(fornecedor);
        conta.setDescricao(dto.getDescricao());
        conta.setDataVencimento(dto.getDataVencimento());
        conta.setValor(dto.getValor());
        conta.setDataPagamento(dto.getDataPagamento());
        conta.setStatusPagamento(StatusPagamento.valueOf(dto.getStatusPagamento()));

        contasPagarRepository.save(conta);

        return toResponseDTO(conta);
    }

    // Delete
    public void excluir(Long id)
    {
        ContasPagar conta = contasPagarRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta a pagar não encontrada."));
        contasPagarRepository.delete(conta);
    }

    // ✅ Conversor auxiliar
    private ContasPagarResponseDTO toResponseDTO(ContasPagar conta)
    {
        return new ContasPagarResponseDTO(
                conta.getId(),
                conta.getCategoria().getNomeCategoria(),
                conta.getFornecedor().getNomeFantasia(),
                conta.getDescricao(),
                conta.getDataVencimento(),
                conta.getValor(),
                conta.getDataPagamento(),
                conta.getStatusPagamento().toString()
        );
    }
}

package com.example.aula.service;

import com.example.aula.model.financeiro.Fornecedor;
import com.example.aula.repository.FornecedorRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import java.util.List;

@Service
@Validated
public class FornecedorService
{
    private FornecedorRepository fornecedorRepository;

    public FornecedorService(FornecedorRepository fornecedorRepository)
    {
        this.fornecedorRepository = fornecedorRepository;
    }

    public List<Fornecedor> listarTodos()
    {
        return fornecedorRepository.findAll();
    }

    public Fornecedor salvar(@Valid Fornecedor fornecedor)
    {
        return fornecedorRepository.save(fornecedor);
    }

    public Fornecedor atualizar(@Valid Fornecedor fornecedor)
    {
        Fornecedor fornecedorAtualizar = fornecedorRepository.findById(fornecedor.getId())
                .orElseThrow(() -> new IllegalArgumentException("Fornecedor não encontrado."));

        fornecedorAtualizar.setNomeFantasia(fornecedor.getNomeFantasia());
        fornecedorAtualizar.setRazaoSocial(fornecedor.getRazaoSocial());
        fornecedorAtualizar.setTipoDocumento(fornecedor.getTipoDocumento());
        fornecedorAtualizar.setCpf(fornecedor.getCpf());
        fornecedorAtualizar.setCnpj(fornecedor.getCnpj());
        fornecedorAtualizar.setTelefone(fornecedor.getTelefone());
        fornecedorAtualizar.setEmail(fornecedor.getEmail());

        return fornecedorRepository.save(fornecedorAtualizar);
    }

    public void excluir(Long id)
    {
        Fornecedor fornecedorExcluir = fornecedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("pedido não encontrado."));
        // Verifica se o fornecedor está em estoque antes de excluir

        fornecedorRepository.deleteById(fornecedorExcluir.getId());
    }

}

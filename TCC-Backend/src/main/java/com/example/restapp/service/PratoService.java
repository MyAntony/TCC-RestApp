package com.example.restapp.service;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.example.restapp.model.Prato;
import com.example.restapp.repository.PratoRepository;

import java.util.List;

@Service
@Validated
public class PratoService
{
    private PratoRepository pratoRepository;

    public PratoService(PratoRepository pratoRepository)
    {
        this.pratoRepository = pratoRepository;
    }

    public List<Prato> listarTodos()
    {
        return pratoRepository.findAll();
    }

    public Prato salvar(@Valid Prato prato)
    {
        return pratoRepository.save(prato);
    }

    public Prato atualizar(@Valid Prato prato)
    {
        Prato pratoAtualizar = pratoRepository.findById(prato.getId())
                .orElseThrow(() -> new IllegalArgumentException("Prato não encontrado."));

        pratoAtualizar.setNome(prato.getNome());
     
        pratoAtualizar.setId(prato.getId());
        pratoAtualizar.setNome(prato.getNome());
        pratoAtualizar.setDescricao(prato.getDescricao());
        pratoAtualizar.setPreco(prato.getPreco());
        pratoAtualizar.setCategoria(prato.getCategoria());
        pratoAtualizar.setUrl(prato.getUrl());
        pratoAtualizar.setDisponibilidade(prato.getDisponibilidade());
        
        

        return pratoRepository.save(pratoAtualizar);
    }

    public void excluir(Long id)
    {
        Prato pratoExcluir = pratoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("pedido não encontrado."));
        // Verifica se o prato está em estoque antes de excluir

        pratoRepository.deleteById(pratoExcluir.getId());
    }

}

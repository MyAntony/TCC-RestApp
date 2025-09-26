package com.example.aula.service;

import com.example.aula.model.principal.cliente.Cliente;
import com.example.aula.repository.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import java.util.List;

@Service
@Validated
public class ClienteService
{
    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository)
    {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> listarTodos()
    {
        return clienteRepository.findAll();
    }

    public Cliente salvar(@Valid Cliente cliente)
    {
        return clienteRepository.save(cliente);
    }

    public Cliente atualizar(@Valid Cliente cliente)
    {
        Cliente clienteAtualizar = clienteRepository.findById(cliente.getId()).orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado."));
        clienteAtualizar.setNome(cliente.getNome());
        clienteAtualizar.setCpf(cliente.getCpf());
        clienteAtualizar.setTelefone(cliente.getTelefone());
        clienteAtualizar.setEmail(cliente.getEmail());

        return clienteRepository.save(clienteAtualizar);
    }

    public void excluir(Long id)
    {
        Cliente clienteExcluir = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado."));
        // Verifica se o cliente está em estoque antes de excluir

        clienteRepository.deleteById(clienteExcluir.getId());
    }

}

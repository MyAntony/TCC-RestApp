package com.example.restapp.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.example.restapp.dto.cliente.ClienteRequestDTO;
import com.example.restapp.dto.cliente.ClienteResponseDTO;
import com.example.restapp.model.principal.Cliente;
import com.example.restapp.repository.ClienteRepository;

import jakarta.validation.Valid;

@Service
@Validated
public class ClienteService
{
    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteResponseDTO salvar(@Valid ClienteRequestDTO clienteRequestDTO)
    {
        // Converter DTO para entidade
        Cliente cliente = new Cliente();
        cliente.setNome(clienteRequestDTO.getNome());
        cliente.setCpf(clienteRequestDTO.getCpf());
        cliente.setTelefone(clienteRequestDTO.getTelefone());
        cliente.setEmail(clienteRequestDTO.getEmail());
        cliente.setEnderecos(clienteRequestDTO.getEnderecos());

        return toResponseDTO(clienteRepository.save(cliente));
    }

    public List<ClienteResponseDTO> listarTodos()
    {
        return clienteRepository.findAll().stream().map(this::toResponseDTO).toList();
    }

    public ClienteResponseDTO buscarPorIdAuxiliar(UUID idAuxiliar)
    {
        Cliente cliente = clienteRepository.findByIdAuxiliar(idAuxiliar)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado."));
        return toResponseDTO(cliente);
    }

    @Transactional
    public ClienteResponseDTO atualizar(UUID idAuxiliar, @Valid ClienteRequestDTO clienteRequestDTO)
    {
        Cliente clienteAtualizar = clienteRepository.findByIdAuxiliar(idAuxiliar).orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado."));
        clienteAtualizar.setNome(clienteRequestDTO.getNome());
        clienteAtualizar.setCpf(clienteRequestDTO.getCpf());
        clienteAtualizar.setTelefone(clienteRequestDTO.getTelefone());
        clienteAtualizar.setEmail(clienteRequestDTO.getEmail());
        clienteAtualizar.getEnderecos().clear();

        if (clienteRequestDTO.getEnderecos() != null)
        {
            clienteAtualizar.getEnderecos().addAll(clienteRequestDTO.getEnderecos());
        }

        return toResponseDTO(clienteRepository.save(clienteAtualizar));
    }

    public void excluir(Long id)
    {
        Cliente clienteExcluir = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado."));
        // Verifica se o cliente está em estoque antes de excluir

        clienteRepository.deleteById(clienteExcluir.getId());
    }

    private ClienteResponseDTO toResponseDTO(Cliente cliente)
    {
        ClienteResponseDTO clienteResponseDTO = new ClienteResponseDTO();
        clienteResponseDTO.setIdAuxiliar(cliente.getIdAuxiliar());
        clienteResponseDTO.setNome(cliente.getNome());
        clienteResponseDTO.setEnderecos(cliente.getEnderecos());
        
        
        return clienteResponseDTO;
    }

}

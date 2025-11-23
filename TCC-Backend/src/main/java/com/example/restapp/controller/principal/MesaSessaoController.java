package com.example.restapp.controller.principal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.example.restapp.dto.MesaSessaoDTO.*;
import com.example.restapp.service.MesaSessaoService;

@RestController
@RequestMapping("principal/mesas")
@CrossOrigin(origins = "*")
public class MesaSessaoController
{
    @Autowired
    private MesaSessaoService mesaService;

    // Create
    @PostMapping
    public ResponseEntity<MesaSessaoResponseDTO> criarMesa(@RequestBody MesaSessaoRequestDTO mesaRequestDTO)
    {
        MesaSessaoResponseDTO mesaResponseDTO = mesaService.salvar(mesaRequestDTO);
        return new ResponseEntity<>(mesaResponseDTO, HttpStatus.CREATED);
    }

    // @GetMapping
    // public ResponseEntity<List<MesaResponseDTO>> listarMesas()
    // {
    //     List<MesaResponseDTO> mesas = mesaService.listarTodas();
    //     return new ResponseEntity<>(mesas, HttpStatus.OK);
    // }


    // Read
    @GetMapping("/{id}")
    public ResponseEntity<MesaSessaoResponseDTO> obterMesaPorId(@PathVariable Long id)
    {
        MesaSessaoResponseDTO mesaResponseDTO = mesaService.obterPorId(id);
        return new ResponseEntity<>(mesaResponseDTO, HttpStatus.OK);
    }

    // Read todas as mesas
    @GetMapping("/abertas")
    public ResponseEntity<List<MesaSessaoResponseDTO>> listarMesasAbertas()
    {
        List<MesaSessaoResponseDTO> mesas = mesaService.listarMesasAbertas();
        return new ResponseEntity<>(mesas, HttpStatus.OK);
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<MesaSessaoResponseDTO> atualizarMesa(@PathVariable Long id, @RequestBody MesaSessaoRequestDTO mesaRequestDTO)
    {
        MesaSessaoResponseDTO mesaResponseDTO = mesaService.atualizar(id, mesaRequestDTO);
        return new ResponseEntity<>(mesaResponseDTO, HttpStatus.OK);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMesa(@PathVariable Long id)
    {
        mesaService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
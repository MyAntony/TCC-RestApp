package com.example.restapp.controller.principal;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restapp.dto.MesaSessaoDTO.MesaSessaoRequestDTO;
import com.example.restapp.dto.MesaSessaoDTO.MesaSessaoResponseDTO;
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

    @GetMapping("/por-numero/{numero}")
    public ResponseEntity<MesaSessaoResponseDTO> buscarPorNumero(@PathVariable Long numero)
    {
        return ResponseEntity.ok(mesaService.buscarPorMesaId(numero));
    }

    // Read todas as mesas
    @GetMapping("/abertas")
    public ResponseEntity<List<Map<String, Object>>> listarMesasAbertas()
    {
        List<Map<String, Object>> mesas = mesaService.listarMesasAbertas();
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
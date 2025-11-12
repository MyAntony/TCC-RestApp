package com.example.restapp.controller.principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.example.restapp.dto.MesaDTO.*;
import com.example.restapp.service.MesaService;

@RestController
@RequestMapping("principal/mesas")
@CrossOrigin(origins = "*")
public class MesaController
{
    @Autowired
    private MesaService mesaService;

    // Create
    @PostMapping
    public ResponseEntity<MesaResponseDTO> criarMesa(@RequestBody MesaRequestDTO mesaRequestDTO)
    {
        MesaResponseDTO mesaResponseDTO = mesaService.salvar(mesaRequestDTO);
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
    public ResponseEntity<MesaResponseDTO> obterMesaPorId(@PathVariable Long id)
    {
        MesaResponseDTO mesaResponseDTO = mesaService.obterPorId(id);
        return new ResponseEntity<>(mesaResponseDTO, HttpStatus.OK);
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<MesaResponseDTO> atualizarMesa(@PathVariable Long id, @RequestBody MesaRequestDTO mesaRequestDTO)
    {
        MesaResponseDTO mesaResponseDTO = mesaService.atualizar(id, mesaRequestDTO);
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
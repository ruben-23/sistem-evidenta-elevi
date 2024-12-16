package com.liceu.sistem_evidenta_elevi.controller;

import com.liceu.sistem_evidenta_elevi.dto.*;
import com.liceu.sistem_evidenta_elevi.entity.*;
import com.liceu.sistem_evidenta_elevi.mapper.ClasaMapper;
import com.liceu.sistem_evidenta_elevi.mapper.ElevMapper;
import com.liceu.sistem_evidenta_elevi.service.ClasaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("liceu/clase")
public class ClasaController {

    private final ClasaService clasaService;
    private final ClasaMapper clasaMapper;
    private final ElevMapper elevMapper;

    @Autowired
    public ClasaController(ClasaService clasaService, ClasaMapper clasaMapper,
                           ElevMapper elevMapper) {
        this.clasaService = clasaService;
        this.clasaMapper = clasaMapper;
        this.elevMapper = elevMapper;
    }

    @GetMapping
    public ResponseEntity<List<ClasaDTO>> getAllClase() {
        List<Clasa> clase = clasaService.getAllClase();
        return ResponseEntity.ok(clasaMapper.toDTOList(clase));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClasaDTO> getClasaById(@PathVariable Integer id) {
        Clasa clasa = clasaService.getClasaById(id);
        return ResponseEntity.ok(clasaMapper.toDTO(clasa));
    }

    @PostMapping
    public ResponseEntity<ClasaDTO> adaugaClasa(@RequestBody ClasaDTO clasaDTO) {
        Clasa clasa = clasaService.adaugaClasa(clasaDTO);
        return ResponseEntity.ok(clasaMapper.toDTO(clasa));
    }

    @PutMapping("{id}")
    public ResponseEntity<ClasaDTO> actualizareClasa(@PathVariable Integer id, @RequestBody ClasaDTO clasaDTO) {
        clasaDTO.setIdClasa(id);
        Clasa clasa = clasaService.actualizareClasa(clasaDTO);
        return ResponseEntity.ok(clasaMapper.toDTO(clasa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> stergeClasa(@PathVariable Integer id) {
        clasaService.stergeClasa(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/elevi")
    public ResponseEntity<List<ElevDTO>> getEleviByClasa(@PathVariable Integer id) {
        List<Elev> elevi = clasaService.getEleviByClasa(id);
        return ResponseEntity.ok(elevMapper.toDTOList(elevi));
    }

}

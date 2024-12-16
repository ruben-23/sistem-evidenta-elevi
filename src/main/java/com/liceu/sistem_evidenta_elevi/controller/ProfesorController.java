package com.liceu.sistem_evidenta_elevi.controller;

import com.liceu.sistem_evidenta_elevi.dto.ProfesorDTO;
import com.liceu.sistem_evidenta_elevi.entity.Profesor;
import com.liceu.sistem_evidenta_elevi.mapper.ProfesorMapper;
import com.liceu.sistem_evidenta_elevi.service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("liceu/profesori")
public class ProfesorController {

    private final ProfesorService profesorService;
    private final ProfesorMapper profesorMapper;

    @Autowired
    public ProfesorController(ProfesorService profesorService, ProfesorMapper profesorMapper) {
        this.profesorService = profesorService;
        this.profesorMapper = profesorMapper;
    }

    @GetMapping
    public ResponseEntity<List<ProfesorDTO>> getAllProfesori(){
        List<Profesor> profesori = profesorService.getAllProfesori();
        return ResponseEntity.ok(profesorMapper.toDTOList(profesori));
    }

    @GetMapping("{id}")
    public ResponseEntity<ProfesorDTO> getProfesorById(@PathVariable("id") Integer idProfesor){
        Profesor profesor = profesorService.getProfesorById(idProfesor);
        return ResponseEntity.ok(profesorMapper.toDTO(profesor));
    }

    @PutMapping("{id}")
    public ResponseEntity<ProfesorDTO> actualizeazaProfesor(@PathVariable("id") Integer idProfesor, @RequestBody ProfesorDTO profesorDTO){
        profesorDTO.setIdProfesor(idProfesor);
        Profesor profesorActualizat = profesorService.actualizareProfesor(profesorDTO);
        return ResponseEntity.ok(profesorMapper.toDTO(profesorActualizat));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> stergeProfesor(@PathVariable("id") Integer idProfesor){
        profesorService.stergeProfesor(idProfesor);
        return ResponseEntity.noContent().build();
    }

}

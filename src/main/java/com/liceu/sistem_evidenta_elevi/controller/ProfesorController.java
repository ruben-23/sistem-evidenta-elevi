package com.liceu.sistem_evidenta_elevi.controller;

import com.liceu.sistem_evidenta_elevi.dto.ProfesorDTO;
import com.liceu.sistem_evidenta_elevi.entity.Profesor;
import com.liceu.sistem_evidenta_elevi.service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("liceu/profesori")
public class ProfesorController {

    private final ProfesorService profesorService;

    @Autowired
    public ProfesorController(ProfesorService profesorService) {
        this.profesorService = profesorService;
    }

    @GetMapping
    public ResponseEntity<List<Profesor>> getAllProfesori(){
        List<Profesor> profesori = profesorService.getAllProfesori();
        return new ResponseEntity<>(profesori, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Profesor> getProfesorById(@PathVariable("id") Integer idProfesor){
        Profesor profesor = profesorService.getProfesorById(idProfesor);
        return new ResponseEntity<>(profesor, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Profesor> adaugaProfesor(@RequestBody ProfesorDTO profesorDTO){
        Profesor profesorSalvat = profesorService.adaugaProfesor(profesorDTO);
        return new ResponseEntity<>(profesorSalvat, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Profesor> actualizeazaProfesor(@PathVariable("id") Integer idProfesor, @RequestBody ProfesorDTO profesorDTO){
        profesorDTO.setIdProfesor(idProfesor);
        Profesor profesorActualizat = profesorService.actualizareProfesor(profesorDTO);
        return new ResponseEntity<>(profesorActualizat, HttpStatus.OK);
    }


}

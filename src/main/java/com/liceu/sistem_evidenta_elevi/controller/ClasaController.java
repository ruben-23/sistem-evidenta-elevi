package com.liceu.sistem_evidenta_elevi.controller;

import com.liceu.sistem_evidenta_elevi.dto.AbsentaRequestDTO;
import com.liceu.sistem_evidenta_elevi.dto.ClasaRequestDTO;
import com.liceu.sistem_evidenta_elevi.dto.ElevRequestDTO;
import com.liceu.sistem_evidenta_elevi.dto.NotaRequestDTO;
import com.liceu.sistem_evidenta_elevi.entity.*;
import com.liceu.sistem_evidenta_elevi.service.ClasaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("liceu/clase")
public class ClasaController {

    private ClasaService clasaService;

    @Autowired
    public ClasaController(ClasaService clasaService) {
        this.clasaService = clasaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Clasa> getClasaById(@PathVariable Integer id) {
        return ResponseEntity.ok(clasaService.getClasaById(id));
    }

    @PostMapping
    public ResponseEntity<Clasa> adaugaClasa(@RequestBody ClasaRequestDTO clasaRequest) {
        return ResponseEntity.ok(clasaService.adaugaClasa(clasaRequest));
    }

    @PutMapping
    public ResponseEntity<Clasa> actualizareClasa(@RequestBody ClasaRequestDTO clasaRequest) {
        return ResponseEntity.ok(clasaService.actualizareClasa(clasaRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> stergeClasa(@PathVariable Integer id) {
        clasaService.stergeClasa(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/elevi")
    public ResponseEntity<Set<Elev>> getEleviByClasa(@PathVariable Integer id) {
        return ResponseEntity.ok(clasaService.getEleviByClasa(id));
    }

    @PostMapping("/elevi")
    public ResponseEntity<Elev> adaugaElevInClasa(@RequestBody ElevRequestDTO elevRequest) {
        return ResponseEntity.ok(clasaService.adaugaElevInClasa(elevRequest));
    }

    @PutMapping("/elevi")
    public ResponseEntity<Elev> actualizareElev(@RequestBody ElevRequestDTO elevRequest) {
        return ResponseEntity.ok(clasaService.actualizareElev(elevRequest));
    }

    @DeleteMapping("/elevi/{id}")
    public ResponseEntity<Void> stergeElev(@PathVariable Integer id) {
        clasaService.stergeElev(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/note")
    public ResponseEntity<Nota> adaugaNotaElev(@RequestBody NotaRequestDTO notaRequest) {
        return ResponseEntity.ok(clasaService.adaugaNotaElev(notaRequest));
    }

    @PutMapping("/note")
    public ResponseEntity<Nota> actualizareNotaElev(@RequestBody NotaRequestDTO notaRequest) {
        return ResponseEntity.ok(clasaService.actualizareNotaElev(notaRequest));
    }

    @DeleteMapping("/note/{id}")
    public ResponseEntity<Void> stergeNotaElev(@PathVariable Integer id) {
        clasaService.stergeNotaElev(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/absente")
    public ResponseEntity<Absenta> adaugaAbsentaElev(@RequestBody AbsentaRequestDTO absentaRequest) {
        return ResponseEntity.ok(clasaService.adaugaAbsentaElev(absentaRequest));
    }

    @PutMapping("/absente")
    public ResponseEntity<Absenta> actualizareAbsentaElev(@RequestBody AbsentaRequestDTO absentaRequest) {
        return ResponseEntity.ok(clasaService.actualizareAbsentaElev(absentaRequest));
    }

    @DeleteMapping("/absente/{id}")
    public ResponseEntity<Void> stergeAbsentaElev(@PathVariable Integer id) {
        clasaService.stergeAbsentaElev(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/materii")
    public ResponseEntity<List<Materie>> getMateriiDinClasa(@PathVariable Integer id) {
        return ResponseEntity.ok(clasaService.getMateriiDinClasa(id));
    }

    @PostMapping("/{id}/materii")
    public ResponseEntity<ClasaMaterieProfesor> adaugaMaterieSiProfesorLaClasa(
            @PathVariable Integer id,
            @RequestParam Integer idProfesor,
            @RequestParam Integer idMaterie) {
        return ResponseEntity.ok(clasaService.adaugaMaterieSiProfesorLaClasa(id, idProfesor, idMaterie));
    }

    @DeleteMapping("/{id}/materii")
    public ResponseEntity<Void> stergereMaterieSiProfesorDinClasa(
            @PathVariable Integer id,
            @RequestParam Integer idProfesor,
            @RequestParam Integer idMaterie) {
        clasaService.stergereMaterieSiProfesorDinClasa(id, idProfesor, idMaterie);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/materii/profesor/{idProfesor}")
    public ResponseEntity<List<Materie>> getMateriiPredateDeProfesorInClasa(
            @PathVariable Integer id,
            @PathVariable Integer idProfesor) {
        return ResponseEntity.ok(clasaService.getMateriiPredateDeProfesorInClasa(id, idProfesor));
    }

    @GetMapping("/profesor/{idProfesor}")
    public ResponseEntity<List<Clasa>> getClaseProfesor(@PathVariable Integer idProfesor) {
        return ResponseEntity.ok(clasaService.getClaseProfesor(idProfesor));
    }

    @GetMapping("/{id}/profesori")
    public ResponseEntity<List<Profesor>> getProfesoriDinClasa(@PathVariable Integer id) {
        return ResponseEntity.ok(clasaService.getProfesoriDinClasa(id));
    }

}

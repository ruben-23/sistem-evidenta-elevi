package com.liceu.sistem_evidenta_elevi.controller;

import com.liceu.sistem_evidenta_elevi.dto.ClasaDTO;
import com.liceu.sistem_evidenta_elevi.dto.ClasaMaterieProfesorDTO;
import com.liceu.sistem_evidenta_elevi.dto.MaterieDTO;
import com.liceu.sistem_evidenta_elevi.dto.ProfesorDTO;
import com.liceu.sistem_evidenta_elevi.entity.Clasa;
import com.liceu.sistem_evidenta_elevi.entity.ClasaMaterieProfesor;
import com.liceu.sistem_evidenta_elevi.entity.Materie;
import com.liceu.sistem_evidenta_elevi.entity.Profesor;
import com.liceu.sistem_evidenta_elevi.mapper.ClasaMapper;
import com.liceu.sistem_evidenta_elevi.mapper.ClasaMaterieProfesorMapper;
import com.liceu.sistem_evidenta_elevi.mapper.MaterieMapper;
import com.liceu.sistem_evidenta_elevi.mapper.ProfesorMapper;
import com.liceu.sistem_evidenta_elevi.service.ClasaMaterieProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller pentru gestionarea cererilor legate de relatiile dintre clase, materii si profesori.
 */
@RestController
@RequestMapping("liceu/clase/materii/profesori")
public class ClasaMaterieProfesorController {

    private final ClasaMaterieProfesorService service;
    private final ClasaMaterieProfesorMapper mapper;
    private final MaterieMapper materieMapper;
    private final ClasaMapper clasaMapper;
    private final ProfesorMapper profesorMapper;

    /**
     * Constructorul clasei ClasaMaterieProfesorController.
     *
     * @param service serviciul pentru gestionarea logicii legate de relatiile dintre clase, materii si profesori.
     * @param mapper mapper-ul pentru conversia intre entitati si DTO-uri pentru relatiile dintre clase, materii si profesori.
     * @param materieMapper mapper-ul pentru conversia intre entitati si DTO-uri pentru materii.
     * @param clasaMapper mapper-ul pentru conversia intre entitati si DTO-uri pentru clase.
     * @param profesorMapper mapper-ul pentru conversia intre entitati si DTO-uri pentru profesori.
     */
    @Autowired
    public ClasaMaterieProfesorController(ClasaMaterieProfesorService service, ClasaMaterieProfesorMapper mapper,
                                          MaterieMapper materieMapper, ClasaMapper clasaMapper,
                                          ProfesorMapper profesorMapper) {
        this.service = service;
        this.mapper = mapper;
        this.materieMapper = materieMapper;
        this.clasaMapper = clasaMapper;
        this.profesorMapper = profesorMapper;
    }

    /**
     * Obtine lista materiilor dintr-o clasa identificata prin ID.
     *
     * @param idClasa ID-ul clasei.
     * @return un ResponseEntity care contine lista materiilor in format DTO.
     */
    @GetMapping("{idClasa}/materii")
    public ResponseEntity<List<MaterieDTO>> getMateriiDinClasa(@PathVariable Integer idClasa) {
        List<Materie> materii  = service.getMateriiDinClasa(idClasa);
        return ResponseEntity.ok(materieMapper.toDTOList(materii));
    }

    /**
     * Obtine lista profesorilor dintr-o clasa identificata prin ID.
     *
     * @param idClasa ID-ul clasei.
     * @return un ResponseEntity care contine lista profesorilor in format DTO.
     */
    @GetMapping("{idClasa}/profesori")
    public ResponseEntity<List<ProfesorDTO>> getProfesoriDinClasa(@PathVariable Integer idClasa) {
        List<Profesor> profesori = service.getProfesoriDinClasa(idClasa);
        return ResponseEntity.ok(profesorMapper.toDTOList(profesori));
    }

    /**
     * Obtine lista claselor in care preda un profesor identificat prin ID.
     *
     * @param idProfesor ID-ul profesorului.
     * @return un ResponseEntity care contine lista claselor in format DTO.
     */
    @GetMapping("{idProfesor}/clase")
    public ResponseEntity<List<ClasaDTO>> getClaseProfesor(@PathVariable Integer idProfesor) {
        List<Clasa> clase = service.getClaseProfesor(idProfesor);
        return ResponseEntity.ok(clasaMapper.toDTOList(clase));
    }

    /**
     * Obtine lista materiilor predate de un profesor intr-o clasa identificata prin ID.
     *
     * @param idClasa ID-ul clasei.
     * @param idProfesor ID-ul profesorului.
     * @return un ResponseEntity care contine lista materiilor in format DTO.
     */
    @GetMapping("/{idClasa}/materii/profesor/{idProfesor}")
    public ResponseEntity<List<MaterieDTO>> getMateriiPredateDeProfesorInClasa(
            @PathVariable Integer idClasa,
            @PathVariable Integer idProfesor) {
        List<Materie> materii = service.getMateriiPredateDeProfesorInClasa(idClasa, idProfesor);
        return ResponseEntity.ok(materieMapper.toDTOList(materii));
    }

    /**
     * Adauga o materie si un profesor la o clasa.
     *
     * @param idClasa ID-ul clasei.
     * @param idProfesor ID-ul profesorului.
     * @param idMaterie ID-ul materiei.
     * @return un ResponseEntity care contine relatia creata in format DTO.
     */
    @PostMapping("/{idClasa}/{idProfesor}/{idMaterie}")
    public ResponseEntity<ClasaMaterieProfesorDTO> adaugaMaterieSiProfesorLaClasa(
            @PathVariable Integer idClasa,
            @PathVariable Integer idProfesor,
            @PathVariable Integer idMaterie) {
        ClasaMaterieProfesor entitate = service.adaugaMaterieSiProfesorLaClasa(idClasa, idProfesor, idMaterie);
        return ResponseEntity.ok(mapper.toDTO(entitate));
    }

    /**
     * Sterge o materie si un profesor dintr-o clasa.
     *
     * @param idClasa ID-ul clasei.
     * @param idProfesor ID-ul profesorului.
     * @param idMaterie ID-ul materiei.
     * @return un ResponseEntity fara continut.
     */
    @DeleteMapping("/{idClasa}/{idProfesor}/{idMaterie}")
    public ResponseEntity<Void> stergereMaterieSiProfesorDinClasa(
            @PathVariable Integer idClasa,
            @PathVariable Integer idProfesor,
            @PathVariable Integer idMaterie) {
        service.stergereMaterieSiProfesorDinClasa(idClasa, idProfesor, idMaterie);
        return ResponseEntity.noContent().build();
    }

}

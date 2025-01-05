package com.liceu.sistem_evidenta_elevi.controller;

import com.liceu.sistem_evidenta_elevi.dto.ProfesorDTO;
import com.liceu.sistem_evidenta_elevi.entity.Profesor;
import com.liceu.sistem_evidenta_elevi.mapper.ProfesorMapper;
import com.liceu.sistem_evidenta_elevi.service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller pentru gestionarea cererilor legate de profesori.
 */
@RestController
@RequestMapping("liceu/profesori")
public class ProfesorController {

    private final ProfesorService profesorService;
    private final ProfesorMapper profesorMapper;

    /**
     * Constructorul clasei ProfesorController.
     *
     * @param profesorService serviciul pentru gestionarea logicii legate de profesori.
     * @param profesorMapper mapper-ul pentru conversia intre entitati si DTO-uri pentru profesori.
     */
    @Autowired
    public ProfesorController(ProfesorService profesorService, ProfesorMapper profesorMapper) {
        this.profesorService = profesorService;
        this.profesorMapper = profesorMapper;
    }

    /**
     * Obtine lista tuturor profesorilor.
     *
     * @return un ResponseEntity care contine lista cu profesori in format DTO.
     */
    @GetMapping
    public ResponseEntity<List<ProfesorDTO>> getAllProfesori(){
        List<Profesor> profesori = profesorService.getAllProfesori();
        return ResponseEntity.ok(profesorMapper.toDTOList(profesori));
    }

    /**
     * Obtine detalii despre un profesor identificat prin ID.
     *
     * @param idProfesor ID-ul profesorului.
     * @return un ResponseEntity care contine profesorul in format DTO.
     */
    @GetMapping("{id}")
    public ResponseEntity<ProfesorDTO> getProfesorById(@PathVariable("id") Integer idProfesor){
        Profesor profesor = profesorService.getProfesorById(idProfesor);
        return ResponseEntity.ok(profesorMapper.toDTO(profesor));
    }

    /**
     * Actualizeaza detaliile unui profesor existent.
     *
     * @param idProfesor ID-ul profesorului care trebuie actualizat.
     * @param profesorDTO DTO-ul cu noile detalii ale profesorului.
     * @return un ResponseEntity care contine profesorul actualizat in format DTO.
     */
    @PutMapping("{id}")
    public ResponseEntity<ProfesorDTO> actualizeazaProfesor(@PathVariable("id") Integer idProfesor, @RequestBody ProfesorDTO profesorDTO){
        profesorDTO.setIdProfesor(idProfesor);
        Profesor profesorActualizat = profesorService.actualizareProfesor(profesorDTO);
        return ResponseEntity.ok(profesorMapper.toDTO(profesorActualizat));
    }

    /**
     * Sterge un profesor identificat prin ID.
     *
     * @param idProfesor ID-ul profesorului care trebuie sters.
     * @return un ResponseEntity fara continut.
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Void> stergeProfesor(@PathVariable("id") Integer idProfesor){
        profesorService.stergeProfesor(idProfesor);
        return ResponseEntity.noContent().build();
    }

}

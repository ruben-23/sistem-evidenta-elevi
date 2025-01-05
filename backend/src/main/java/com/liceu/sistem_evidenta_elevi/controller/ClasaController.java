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

/**
 * Controller pentru gestionarea cererilor legate de clase.
 */
@RestController
@RequestMapping("liceu/clase")
public class ClasaController {

    private final ClasaService clasaService;
    private final ClasaMapper clasaMapper;
    private final ElevMapper elevMapper;

    /**
     * Constructorul clasei ClasaController.
     *
     * @param clasaService serviciul pentru gestionarea logicii legate de clase.
     * @param clasaMapper mapper-ul pentru conversia intre entitati si DTO-uri pentru clase.
     * @param elevMapper mapper-ul pentru conversia intre entitati si DTO-uri pentru elevi.
     */
    @Autowired
    public ClasaController(ClasaService clasaService, ClasaMapper clasaMapper,
                           ElevMapper elevMapper) {
        this.clasaService = clasaService;
        this.clasaMapper = clasaMapper;
        this.elevMapper = elevMapper;
    }

    /**
     * Obtine lista tuturor claselor.
     *
     * @return un ResponseEntity care contine lista cu clase in format DTO.
     */
    @GetMapping
    public ResponseEntity<List<ClasaDTO>> getAllClase() {
        List<Clasa> clase = clasaService.getAllClase();
        return ResponseEntity.ok(clasaMapper.toDTOList(clase));
    }

    /**
     * Obtine detalii despre o clasa identificata prin ID.
     *
     * @param id ID-ul clasei.
     * @return un ResponseEntity care contine clasa in format DTO.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ClasaDTO> getClasaById(@PathVariable Integer id) {
        Clasa clasa = clasaService.getClasaById(id);
        return ResponseEntity.ok(clasaMapper.toDTO(clasa));
    }

    /**
     * Adauga o noua clasa.
     *
     * @param clasaDTO clasa care trebuie sa fie adaugata.
     * @return un ResponseEntity care contine clasa dupa adaugare in format DTO.
     */

    @PostMapping
    public ResponseEntity<ClasaDTO> adaugaClasa(@RequestBody ClasaDTO clasaDTO) {
        Clasa clasa = clasaService.adaugaClasa(clasaDTO);
        return ResponseEntity.ok(clasaMapper.toDTO(clasa));
    }

    /**
     * Actualizeaza o clasa existenta.
     *
     * @param id ID-ul clasei care trebuie actualizata.
     * @param clasaDTO informatiile ce trebuie actualizate pentru clasa.
     * @return un ResponseEntity care contine clasa actualizata in format DTO.
     */
    @PutMapping("{id}")
    public ResponseEntity<ClasaDTO> actualizareClasa(@PathVariable Integer id, @RequestBody ClasaDTO clasaDTO) {
        clasaDTO.setIdClasa(id);
        Clasa clasa = clasaService.actualizareClasa(clasaDTO);
        return ResponseEntity.ok(clasaMapper.toDTO(clasa));
    }

    /**
     * Sterge o clasa pe baza ID-ului.
     *
     * @param id ID-ul clasei care urmeaza sa fie stearsa.
     * @return un ResponseEntity fara continut.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> stergeClasa(@PathVariable Integer id) {
        clasaService.stergeClasa(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Obtine lista elevilor dintr-o clasa identificata prin ID.
     *
     * @param id ID-ul clasei.
     * @return un ResponseEntity care contine lista elevilor in format DTO.
     */
    @GetMapping("/{id}/elevi")
    public ResponseEntity<List<ElevDTO>> getEleviByClasa(@PathVariable Integer id) {
        List<Elev> elevi = clasaService.getEleviByClasa(id);
        return ResponseEntity.ok(elevMapper.toDTOList(elevi));
    }

}

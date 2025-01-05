package com.liceu.sistem_evidenta_elevi.controller;

import com.liceu.sistem_evidenta_elevi.dto.SpecializareDTO;
import com.liceu.sistem_evidenta_elevi.entity.Specializare;
import com.liceu.sistem_evidenta_elevi.mapper.SpecializareMapper;
import com.liceu.sistem_evidenta_elevi.service.SpecializareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller pentru gestionarea cererilor legate de specializari.
 */
@RestController
@RequestMapping("liceu/specializari")
public class SpecializareController {

    private final SpecializareService specializareService;
    private final SpecializareMapper specializareMapper;

    /**
     * Constructorul clasei SpecializareController.
     *
     * @param specializareService serviciul pentru gestionarea logicii legate de specializari.
     * @param specializareMapper mapper-ul pentru conversia intre entitati si DTO-uri pentru specializari.
     */
    @Autowired
    public SpecializareController(SpecializareService specializareService, SpecializareMapper specializareMapper) {
        this.specializareService = specializareService;
        this.specializareMapper = specializareMapper;
    }

    /**
     * Obtine lista tuturor specializarilor.
     *
     * @return un ResponseEntity care contine lista cu specializari in format DTO.
     */
    @GetMapping
    public ResponseEntity<List<SpecializareDTO>> getSpecializari() {
        List<Specializare> specializari = specializareService.getAllSpecializari();
        return ResponseEntity.ok(specializareMapper.toDTOList(specializari));
    }

    /**
     * Obtine detalii despre o specializare identificata prin ID.
     *
     * @param id ID-ul specializarii.
     * @return un ResponseEntity care contine specializarea in format DTO.
     */
    @GetMapping("{id}")
    public ResponseEntity<SpecializareDTO> getSpecializari(@PathVariable Integer id) {
        Specializare specializare = specializareService.getSpecializareById(id);
        return ResponseEntity.ok(specializareMapper.toDTO(specializare));
    }

    /**
     * Adauga o noua specializare.
     *
     * @param specializareDTO specializarea care trebuie sa fie adaugata.
     * @return un ResponseEntity care contine specializarea dupa adaugare in format DTO.
     */
    @PostMapping
    public ResponseEntity<SpecializareDTO> adaugaSpecializare(@RequestBody SpecializareDTO specializareDTO) {
        Specializare specializare = specializareService.adaugaSpecializare(specializareDTO);
        return ResponseEntity.ok(specializareMapper.toDTO(specializare));
    }

    /**
     * Actualizeaza o specializare existenta.
     *
     * @param id ID-ul specializarii care trebuie actualizata.
     * @param specializareDTO informatiile ce trebuie actualizate pentru specializare.
     * @return un ResponseEntity care contine specializarea actualizata in format DTO.
     */
    @PutMapping("{id}")
    public ResponseEntity<SpecializareDTO> actualizareSpecializare(@PathVariable Integer id, @RequestBody SpecializareDTO specializareDTO) {
        specializareDTO.setIdSpecializare(id);
        Specializare specializare = specializareService.actualizareSpecializare(specializareDTO);
        return ResponseEntity.ok(specializareMapper.toDTO(specializare));
    }

    /**
     * Sterge o specializare pe baza ID-ului.
     *
     * @param id ID-ul specializarii care urmeaza sa fie stearsa.
     * @return un ResponseEntity fara continut.
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Void> stergeSpecializare(@PathVariable Integer id) {
        specializareService.stergeSpecializare(id);
        return ResponseEntity.ok().build();
    }

}

package com.liceu.sistem_evidenta_elevi.controller;

import com.liceu.sistem_evidenta_elevi.dto.BursaDTO;
import com.liceu.sistem_evidenta_elevi.entity.Bursa;
import com.liceu.sistem_evidenta_elevi.mapper.BursaMapper;
import com.liceu.sistem_evidenta_elevi.service.BursaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller pentru gestionarea cererilor legate de burse.
 */
@RestController
@RequestMapping("liceu/burse")
public class BursaController {

    private final BursaService bursaService;
    private final BursaMapper bursaMapper;

    /**
     * Constructorul clasei BursaController.
     *
     * @param bursaService serviciul pentru gestionarea logicii legate de burse.
     * @param bursaMapper mapper-ul pentru conversia intre entitati si DTO-uri.
     */
    @Autowired
    public BursaController(BursaService bursaService, BursaMapper bursaMapper) {
        this.bursaService = bursaService;
        this.bursaMapper = bursaMapper;
    }

    /**
     * Obtine lista tuturor burselor.
     *
     * @return un ResponseEntity care contine lista cu burse in format DTO.
     */
    @GetMapping
    public ResponseEntity<List<BursaDTO>> getAllBurse() {
        List<Bursa> burse = bursaService.getAllBurse();
        return ResponseEntity.ok(bursaMapper.toDTOList(burse));
    }

    /**
     * Obtine detalii despre o bursa identificata prin ID.
     *
     * @param id ID-ul bursei.
     * @return un ResponseEntity care contine bursa in format DTO.
     */
    @GetMapping("{id}")
    public ResponseEntity<BursaDTO> getBursaById(@PathVariable int id) {
        Bursa bursa = bursaService.getBursaById(id);
        return ResponseEntity.ok(bursaMapper.toDTO(bursa));
    }

    /**
     * Adauga o noua bursa.
     *
     * @param bursaDTO bursa care trebuie sa fie adaugata.
     * @return un ResponseEntity care contine bursa dupa adaugare in format DTO.
     */
    @PostMapping
    public ResponseEntity<BursaDTO> adaugaBursa(@RequestBody BursaDTO bursaDTO) {
        Bursa bursa = bursaService.adaugaBursa(bursaDTO);
        return ResponseEntity.ok(bursaMapper.toDTO(bursa));
    }

    /**
     * Actualizeaza o bursa existenta.
     *
     * @param id ID-ul bursei care trebuie actualizata.
     * @param bursaDTO informatiile ce trebuie actualizate pentru bursa.
     * @return un ResponseEntity care contine bursa actualizata in format DTO.
     */
    @PutMapping("{id}")
    public ResponseEntity<BursaDTO> actualizareBursa(@PathVariable Integer id, @RequestBody BursaDTO bursaDTO) {
        bursaDTO.setIdBursa(id);
        Bursa bursa = bursaService.actualizareBursa(bursaDTO);
        return ResponseEntity.ok(bursaMapper.toDTO(bursa));
    }

    /**
     * Sterge o bursa pe baza ID-ului.
     *
     * @param id ID-ul bursei care urmeaza sa fie stearsa.
     * @return un ResponseEntity fara continut.
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Void> stergeBursa(@PathVariable Integer id) {
        bursaService.stergeBursa(id);
        return ResponseEntity.noContent().build();
    }

}

package com.liceu.sistem_evidenta_elevi.controller;

import com.liceu.sistem_evidenta_elevi.dto.SpecializareDTO;
import com.liceu.sistem_evidenta_elevi.entity.Specializare;
import com.liceu.sistem_evidenta_elevi.mapper.SpecializareMapper;
import com.liceu.sistem_evidenta_elevi.service.SpecializareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("liceu/specializari")
public class SpecializareController {

    private final SpecializareService specializareService;
    private final SpecializareMapper specializareMapper;

    @Autowired
    public SpecializareController(SpecializareService specializareService, SpecializareMapper specializareMapper) {
        this.specializareService = specializareService;
        this.specializareMapper = specializareMapper;
    }

    @GetMapping
    public ResponseEntity<List<SpecializareDTO>> getSpecializari() {
        List<Specializare> specializari = specializareService.getAllSpecializari();
        return ResponseEntity.ok(specializareMapper.toDTOList(specializari));
    }

    @GetMapping("{id}")
    public ResponseEntity<SpecializareDTO> getSpecializari(@PathVariable Integer id) {
        Specializare specializare = specializareService.getSpecializareById(id);
        return ResponseEntity.ok(specializareMapper.toDTO(specializare));
    }

    @PostMapping
    public ResponseEntity<SpecializareDTO> adaugaSpecializare(@RequestBody SpecializareDTO specializareDTO) {
        Specializare specializare = specializareService.adaugaSpecializare(specializareDTO);
        return ResponseEntity.ok(specializareMapper.toDTO(specializare));
    }

    @PutMapping("{id}")
    public ResponseEntity<SpecializareDTO> actualizareSpecializare(@PathVariable Integer id, @RequestBody SpecializareDTO specializareDTO) {
        specializareDTO.setIdSpecializare(id);
        Specializare specializare = specializareService.actualizareSpecializare(specializareDTO);
        return ResponseEntity.ok(specializareMapper.toDTO(specializare));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> stergeSpecializare(@PathVariable Integer id) {
        specializareService.stergeSpecializare(id);
        return ResponseEntity.ok().build();
    }

}

package com.liceu.sistem_evidenta_elevi.controller;

import com.liceu.sistem_evidenta_elevi.dto.SpecializareRequestDTO;
import com.liceu.sistem_evidenta_elevi.entity.Specializare;
import com.liceu.sistem_evidenta_elevi.service.SpecializareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("liceu/specializari")
public class SpecializareController {

    private final SpecializareService specializareService;

    @Autowired
    public SpecializareController(SpecializareService specializareService) {
        this.specializareService = specializareService;
    }

    @GetMapping
    public ResponseEntity<List<Specializare>> getSpecializare() {
        return ResponseEntity.ok(specializareService.getAllSpecializari());
    }

    @PostMapping
    public ResponseEntity<Specializare> getSpecializare(@RequestBody SpecializareRequestDTO specializareRequest) {
        return ResponseEntity.ok(specializareService.adaugaSpecializare(specializareRequest));
    }


}

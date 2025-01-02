package com.liceu.sistem_evidenta_elevi.controller;

import com.liceu.sistem_evidenta_elevi.dto.BursaDTO;
import com.liceu.sistem_evidenta_elevi.entity.Bursa;
import com.liceu.sistem_evidenta_elevi.mapper.BursaMapper;
import com.liceu.sistem_evidenta_elevi.service.BursaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("liceu/burse")
public class BursaController {

    private final BursaService bursaService;
    private final BursaMapper bursaMapper;

    @Autowired
    public BursaController(BursaService bursaService, BursaMapper bursaMapper) {
        this.bursaService = bursaService;
        this.bursaMapper = bursaMapper;
    }

    @GetMapping
    public ResponseEntity<List<BursaDTO>> getAllBurse() {
        List<Bursa> burse = bursaService.getAllBurse();
        return ResponseEntity.ok(bursaMapper.toDTOList(burse));
    }

    @GetMapping("{id}")
    public ResponseEntity<BursaDTO> getBursaById(@PathVariable int id) {
        Bursa bursa = bursaService.getBursaById(id);
        return ResponseEntity.ok(bursaMapper.toDTO(bursa));
    }

    @PostMapping
    public ResponseEntity<BursaDTO> adaugaBursa(@RequestBody BursaDTO bursaDTO) {
        Bursa bursa = bursaService.adaugaBursa(bursaDTO);
        return ResponseEntity.ok(bursaMapper.toDTO(bursa));
    }

    @PutMapping("{id}")
    public ResponseEntity<BursaDTO> actualizareBursa(@PathVariable Integer id, @RequestBody BursaDTO bursaDTO) {
        bursaDTO.setIdBursa(id);
        Bursa bursa = bursaService.actualizareBursa(bursaDTO);
        return ResponseEntity.ok(bursaMapper.toDTO(bursa));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> stergeBursa(@PathVariable Integer id) {
        bursaService.stergeBursa(id);
        return ResponseEntity.noContent().build();
    }

}

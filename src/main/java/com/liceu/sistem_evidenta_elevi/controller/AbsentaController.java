package com.liceu.sistem_evidenta_elevi.controller;

import com.liceu.sistem_evidenta_elevi.dto.AbsentaDTO;
import com.liceu.sistem_evidenta_elevi.entity.Absenta;
import com.liceu.sistem_evidenta_elevi.mapper.AbsentaMapper;
import com.liceu.sistem_evidenta_elevi.service.AbsentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("liceu/absente")
public class AbsentaController {

    private final AbsentaService absentaService;
    private final AbsentaMapper absentaMapper;

    @Autowired
    public AbsentaController(AbsentaService absentaService, AbsentaMapper absentaMapper) {
        this.absentaService = absentaService;
        this.absentaMapper = absentaMapper;
    }

    @GetMapping
    public ResponseEntity<List<AbsentaDTO>> getAllAbsente(){
        List<Absenta> absente = absentaService.getAllAbsente();
        return ResponseEntity.ok(absentaMapper.toDTOList(absente));
    }

    @GetMapping("{id}")
    public ResponseEntity<AbsentaDTO> getAbsentaById(@PathVariable int id){
        Absenta absente = absentaService.getAbsentaById(id);
        return ResponseEntity.ok(absentaMapper.toDTO(absente));
    }

    @PostMapping
    public ResponseEntity<AbsentaDTO> adaugaAbsenta(@RequestBody AbsentaDTO absentaDTO){
        Absenta absenta = absentaService.adaugaAbsenta(absentaDTO);
        return ResponseEntity.ok(absentaMapper.toDTO(absenta));
    }

    @PutMapping("{id}")
    public ResponseEntity<AbsentaDTO> actualizeaAbsenta(@PathVariable int id, @RequestBody AbsentaDTO absentaDTO){
        absentaDTO.setIdElev(id);
        Absenta absenta = absentaService.actualizareAbsenta(absentaDTO);
        return ResponseEntity.ok(absentaMapper.toDTO(absenta));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> stergereAbsenta(@PathVariable("id") Integer idAbsenta) {
        absentaService.stergeAbsenta(idAbsenta);
        return ResponseEntity.noContent().build();
    }

}

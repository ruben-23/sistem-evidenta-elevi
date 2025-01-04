package com.liceu.sistem_evidenta_elevi.controller;

import com.liceu.sistem_evidenta_elevi.dto.NotaDTO;
import com.liceu.sistem_evidenta_elevi.entity.Nota;
import com.liceu.sistem_evidenta_elevi.mapper.NotaMapper;
import com.liceu.sistem_evidenta_elevi.service.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("liceu/note")
public class NotaController {

    private NotaService notaService;
    private NotaMapper notaMapper;

    @Autowired
    public NotaController(NotaService notaService, NotaMapper notaMapper) {
        this.notaService = notaService;
        this.notaMapper = notaMapper;
    }

    @GetMapping
    public ResponseEntity<List<NotaDTO>> getAllNote(){
        List<Nota> note = notaService.getAllNote();
        return ResponseEntity.ok(notaMapper.toDTOList(note));
    }

    @GetMapping("{id}")
    public ResponseEntity<NotaDTO> getNotaById(@PathVariable int id){
        Nota note = notaService.getNotaById(id);
        return ResponseEntity.ok(notaMapper.toDTO(note));
    }

    @PostMapping
    public ResponseEntity<NotaDTO> adaugaNota(@RequestBody NotaDTO notaDTO){
        Nota nota = notaService.adaugaNota(notaDTO);
        return ResponseEntity.ok(notaMapper.toDTO(nota));
    }

    @PutMapping("{id}")
    public ResponseEntity<NotaDTO> actualizeaNota(@PathVariable int id, @RequestBody NotaDTO notaDTO){
        notaDTO.setIdElev(id);
        Nota nota = notaService.actualizareNota(notaDTO);
        return ResponseEntity.ok(notaMapper.toDTO(nota));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> stergereNota(@PathVariable("id") Integer idNota) {
        notaService.stergeNota(idNota);
        return ResponseEntity.noContent().build();
    }

}

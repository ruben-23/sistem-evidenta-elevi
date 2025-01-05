package com.liceu.sistem_evidenta_elevi.controller;

import com.liceu.sistem_evidenta_elevi.dto.NotaDTO;
import com.liceu.sistem_evidenta_elevi.entity.Nota;
import com.liceu.sistem_evidenta_elevi.mapper.NotaMapper;
import com.liceu.sistem_evidenta_elevi.service.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller pentru gestionarea cererilor legate de note.
 */
@RestController
@RequestMapping("liceu/note")
public class NotaController {

    private NotaService notaService;
    private NotaMapper notaMapper;

    /**
     * Constructorul clasei NotaController.
     *
     * @param notaService serviciul pentru gestionarea logicii legate de note.
     * @param notaMapper mapper-ul pentru conversia intre entitati si DTO-uri pentru note.
     */
    @Autowired
    public NotaController(NotaService notaService, NotaMapper notaMapper) {
        this.notaService = notaService;
        this.notaMapper = notaMapper;
    }

    /**
     * Obtine lista tuturor notelor.
     *
     * @return un ResponseEntity care contine lista cu note in format DTO.
     */
    @GetMapping
    public ResponseEntity<List<NotaDTO>> getAllNote(){
        List<Nota> note = notaService.getAllNote();
        return ResponseEntity.ok(notaMapper.toDTOList(note));
    }

    /**
     * Obtine detalii despre o nota identificata prin ID.
     *
     * @param id ID-ul notei.
     * @return un ResponseEntity care contine nota in format DTO.
     */
    @GetMapping("{id}")
    public ResponseEntity<NotaDTO> getNotaById(@PathVariable int id){
        Nota note = notaService.getNotaById(id);
        return ResponseEntity.ok(notaMapper.toDTO(note));
    }

    /**
     * Adauga o noua nota.
     *
     * @param notaDTO nota care trebuie sa fie adaugata.
     * @return un ResponseEntity care contine nota dupa adaugare in format DTO.
     */
    @PostMapping
    public ResponseEntity<NotaDTO> adaugaNota(@RequestBody NotaDTO notaDTO){
        Nota nota = notaService.adaugaNota(notaDTO);
        return ResponseEntity.ok(notaMapper.toDTO(nota));
    }

    /**
     * Actualizeaza o nota existenta.
     *
     * @param id ID-ul notei care trebuie actualizata.
     * @param notaDTO informatiile ce trebuie actualizate pentru nota.
     * @return un ResponseEntity care contine nota actualizata in format DTO.
     */
    @PutMapping("{id}")
    public ResponseEntity<NotaDTO> actualizeaNota(@PathVariable int id, @RequestBody NotaDTO notaDTO){
        notaDTO.setIdElev(id);
        Nota nota = notaService.actualizareNota(notaDTO);
        return ResponseEntity.ok(notaMapper.toDTO(nota));
    }

    /**
     * Sterge o nota pe baza ID-ului.
     *
     * @param idNota ID-ul notei care urmeaza sa fie stearsa.
     * @return un ResponseEntity fara continut.
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Void> stergereNota(@PathVariable("id") Integer idNota) {
        notaService.stergeNota(idNota);
        return ResponseEntity.noContent().build();
    }

}

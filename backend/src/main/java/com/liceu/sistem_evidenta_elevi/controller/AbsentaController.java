package com.liceu.sistem_evidenta_elevi.controller;

import com.liceu.sistem_evidenta_elevi.dto.AbsentaDTO;
import com.liceu.sistem_evidenta_elevi.entity.Absenta;
import com.liceu.sistem_evidenta_elevi.mapper.AbsentaMapper;
import com.liceu.sistem_evidenta_elevi.service.AbsentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Controller pentru gestionarea cererilor legate de absente.
 */
@RestController
@RequestMapping("liceu/absente")
public class AbsentaController {

    private final AbsentaService absentaService;
    private final AbsentaMapper absentaMapper;

    /**
     * Constructorul clasei AbsentaController.
     *
     * @param absentaService serviciul pentru gestionarea logicii legate de absente.
     * @param absentaMapper mapper-ul pentru conversia intre entitati si DTO-uri.
     */
    @Autowired
    public AbsentaController(AbsentaService absentaService, AbsentaMapper absentaMapper) {
        this.absentaService = absentaService;
        this.absentaMapper = absentaMapper;
    }

    /**
     * Obtine lista tuturor absentelor.
     *
     * @return un ResponseEntity care contine lista cu absente in format DTO.
     */
    @GetMapping
    public ResponseEntity<List<AbsentaDTO>> getAllAbsente(){
        List<Absenta> absente = absentaService.getAllAbsente();
        return ResponseEntity.ok(absentaMapper.toDTOList(absente));
    }

    /**
     * Obtine detalii despre o absenta identificata prin ID.
     *
     * @param id ID-ul absentei.
     * @return un ResponseEntity care contine absenta in format DTO.
     */
    @GetMapping("{id}")
    public ResponseEntity<AbsentaDTO> getAbsentaById(@PathVariable int id){
        Absenta absente = absentaService.getAbsentaById(id);
        return ResponseEntity.ok(absentaMapper.toDTO(absente));
    }

    /**
     * Adauga o noua absenta.
     *
     * @param absentaDTO absenta care trebuie sa fie adaugata.
     * @return un ResponseEntity care contine absenta dupa adugare in format DTO.
     */
    @PostMapping
    public ResponseEntity<AbsentaDTO> adaugaAbsenta(@RequestBody AbsentaDTO absentaDTO){
        Absenta absenta = absentaService.adaugaAbsenta(absentaDTO);
        return ResponseEntity.ok(absentaMapper.toDTO(absenta));
    }

    /**
     * Actualizeaza o absenta existenta.
     *
     * @param id ID-ul absentei care trebuie actualizata.
     * @param absentaDTO informatiile ce trebuie actualizate pentru absentea.
     * @return un ResponseEntity care contine absenta actualizata in format DTO.
     */
    @PutMapping("{id}")
    public ResponseEntity<AbsentaDTO> actualizeaAbsenta(@PathVariable int id, @RequestBody AbsentaDTO absentaDTO){
        absentaDTO.setIdElev(id);
        Absenta absenta = absentaService.actualizareAbsenta(absentaDTO);
        return ResponseEntity.ok(absentaMapper.toDTO(absenta));
    }

    /**
     * Sterge o absenta pe baza ID-ului.
     *
     * @param idAbsenta ID-ul absentei care urmeaza sa fie stearsa.
     * @return un ResponseEntity fara continut.
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Void> stergereAbsenta(@PathVariable("id") Integer idAbsenta) {
        absentaService.stergeAbsenta(idAbsenta);
        return ResponseEntity.noContent().build();
    }

}

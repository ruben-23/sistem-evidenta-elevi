package com.liceu.sistem_evidenta_elevi.controller;

import com.liceu.sistem_evidenta_elevi.dto.AbsentaDTO;
import com.liceu.sistem_evidenta_elevi.dto.BursaDTO;
import com.liceu.sistem_evidenta_elevi.dto.ElevDTO;
import com.liceu.sistem_evidenta_elevi.dto.NotaDTO;
import com.liceu.sistem_evidenta_elevi.entity.Absenta;
import com.liceu.sistem_evidenta_elevi.entity.Bursa;
import com.liceu.sistem_evidenta_elevi.entity.Elev;
import com.liceu.sistem_evidenta_elevi.entity.Nota;
import com.liceu.sistem_evidenta_elevi.mapper.AbsentaMapper;
import com.liceu.sistem_evidenta_elevi.mapper.BursaMapper;
import com.liceu.sistem_evidenta_elevi.mapper.ElevMapper;
import com.liceu.sistem_evidenta_elevi.mapper.NotaMapper;
import com.liceu.sistem_evidenta_elevi.service.ElevService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("liceu/elevi")
public class ElevController {

    private final ElevService elevService;
    private final ElevMapper elevMapper;
    private final NotaMapper notaMapper;
    private final AbsentaMapper absentaMapper;
    private final BursaMapper bursaMapper;

    @Autowired
    public ElevController(ElevService elevService, ElevMapper elevMapper,
                          NotaMapper notaMapper, AbsentaMapper absentaMapper, BursaMapper bursaMapper) {
        this.elevService = elevService;
        this.elevMapper = elevMapper;
        this.notaMapper = notaMapper;
        this.absentaMapper = absentaMapper;
        this.bursaMapper = bursaMapper;
    }

    @GetMapping
    public ResponseEntity<List<ElevDTO>> getAllElevi() {
        List<Elev> elevi = elevService.getAllElevi();
        return ResponseEntity.ok(elevMapper.toDTOList(elevi));
    }

    @GetMapping("{id}")
    public ResponseEntity<ElevDTO> getElevById(@PathVariable("id") Integer idElev ) {
        Elev elev = elevService.getElevById(idElev);
        return ResponseEntity.ok(elevMapper.toDTO(elev));
    }

    @GetMapping("{idElev}/{idMaterie}/note")
    public ResponseEntity<List<NotaDTO>> getNoteElevMaterie(@PathVariable Integer idElev, @PathVariable Integer idMaterie) {
        List<Nota> note = elevService.getNoteElevMaterie(idElev, idMaterie);
        return ResponseEntity.ok(notaMapper.toDTOList(note));
    }

    @GetMapping("{idElev}/{idMaterie}/absente")
    public ResponseEntity<List<AbsentaDTO>> getAbsenteElevMaterie(@PathVariable Integer idElev, @PathVariable Integer idMaterie) {
        List<Absenta> absente = elevService.getAbsenteElevMaterie(idElev, idMaterie);
        return ResponseEntity.ok(absentaMapper.toDTOList(absente));
    }

    @GetMapping("/elevi/{idElev}")
    public ResponseEntity<List<BursaDTO>> getBurseElev(@PathVariable int idElev) {
        List<Bursa> burse = elevService.getBurseElev(idElev);
        return ResponseEntity.ok(bursaMapper.toDTOList(burse));
    }

    @PutMapping("{id}")
    public ResponseEntity<ElevDTO> actualizareElev(@PathVariable("id") Integer idElev, @RequestBody ElevDTO elev) {
        elev.setIdElev(idElev);
        Elev elevActualizat = elevService.actualizareElev(elev);
        return ResponseEntity.ok(elevMapper.toDTO(elevActualizat));
    }

    @PutMapping("{idElev}/burse/{idBursa}")
    public ResponseEntity<ElevDTO> adaugaBursaLaELev(@PathVariable Integer idElev, @PathVariable Integer idBursa) {
        Elev elev = elevService.adaugaBursaLaElev(idElev, idBursa);
        return ResponseEntity.ok(elevMapper.toDTO(elev));
    }

    @PostMapping
    public ResponseEntity<ElevDTO> creareElev(@RequestBody ElevDTO elev) {
        Elev elevNou = elevService.adaugaElev(elev);
        return ResponseEntity.ok(elevMapper.toDTO(elevNou));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> stergereElev(@PathVariable("id") Integer idElev) {
        elevService.stergeElev(idElev);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{idElev}/burse/{idBursa}")
    public ResponseEntity<Void> stergereBursaElev(@PathVariable Integer idElev, @PathVariable Integer idBursa) {
        elevService.stergeBursaLaElev(idElev, idBursa);
        return ResponseEntity.noContent().build();
    }




}

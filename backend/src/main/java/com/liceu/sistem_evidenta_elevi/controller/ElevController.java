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

/**
 * Controller pentru gestionarea cererilor legate de elevi.
 */
@RestController
@RequestMapping("liceu/elevi")
public class ElevController {

    private final ElevService elevService;
    private final ElevMapper elevMapper;
    private final NotaMapper notaMapper;
    private final AbsentaMapper absentaMapper;
    private final BursaMapper bursaMapper;

    /**
     * Constructorul clasei ElevController.
     *
     * @param elevService serviciul pentru gestionarea logicii legate de elevi.
     * @param elevMapper mapper-ul pentru conversia intre entitati si DTO-uri pentru elevi.
     * @param notaMapper mapper-ul pentru conversia intre entitati si DTO-uri pentru note.
     * @param absentaMapper mapper-ul pentru conversia intre entitati si DTO-uri pentru absente.
     * @param bursaMapper mapper-ul pentru conversia intre entitati si DTO-uri pentru burse.
     */
    @Autowired
    public ElevController(ElevService elevService, ElevMapper elevMapper,
                          NotaMapper notaMapper, AbsentaMapper absentaMapper, BursaMapper bursaMapper) {
        this.elevService = elevService;
        this.elevMapper = elevMapper;
        this.notaMapper = notaMapper;
        this.absentaMapper = absentaMapper;
        this.bursaMapper = bursaMapper;
    }

    /**
     * Obtine lista tuturor elevilor.
     *
     * @return un ResponseEntity care contine lista cu elevi in format DTO.
     */
    @GetMapping
    public ResponseEntity<List<ElevDTO>> getAllElevi() {
        List<Elev> elevi = elevService.getAllElevi();
        return ResponseEntity.ok(elevMapper.toDTOList(elevi));
    }

    /**
     * Obtine detalii despre un elev identificat prin ID.
     *
     * @param idElev ID-ul elevului.
     * @return un ResponseEntity care contine elevul in format DTO.
     */
    @GetMapping("{id}")
    public ResponseEntity<ElevDTO> getElevById(@PathVariable("id") Integer idElev ) {
        Elev elev = elevService.getElevById(idElev);
        return ResponseEntity.ok(elevMapper.toDTO(elev));
    }

    /**
     * Obtine lista notelor pentru un elev identificat prin ID.
     *
     * @param idElev ID-ul elevului.
     * @return un ResponseEntity care contine lista notelor in format DTO.
     */
    @GetMapping("{idElev}/note")
    public ResponseEntity<List<NotaDTO>> getNoteElev(@PathVariable Integer idElev ) {
        List<Nota> note = elevService.getNoteElev(idElev);
        return ResponseEntity.ok(notaMapper.toDTOList(note));
    }

    /**
     * Obtine lista notelor pentru un elev la o materie specifica.
     *
     * @param idElev ID-ul elevului.
     * @param idMaterie ID-ul materiei.
     * @return un ResponseEntity care contine lista notelor in format DTO.
     */
    @GetMapping("{idElev}/{idMaterie}/note")
    public ResponseEntity<List<NotaDTO>> getNoteElevMaterie(@PathVariable Integer idElev, @PathVariable Integer idMaterie) {
        List<Nota> note = elevService.getNoteElevMaterie(idElev, idMaterie);
        return ResponseEntity.ok(notaMapper.toDTOList(note));
    }

    /**
     * Obtine lista absentelor pentru un elev identificat prin ID.
     *
     * @param idElev ID-ul elevului.
     * @return un ResponseEntity care contine lista absentelor in format DTO.
     */
    @GetMapping("{idElev}/absente")
    public ResponseEntity<List<AbsentaDTO>> getAbsenteElev(@PathVariable Integer idElev ) {
        List<Absenta> absente = elevService.getAbsenteElev(idElev);
        return ResponseEntity.ok(absentaMapper.toDTOList(absente));
    }

    /**
     * Obtine lista absentelor pentru un elev la o materie specifica.
     *
     * @param idElev ID-ul elevului.
     * @param idMaterie ID-ul materiei.
     * @return un ResponseEntity care contine lista absentelor in format DTO.
     */
    @GetMapping("{idElev}/{idMaterie}/absente")
    public ResponseEntity<List<AbsentaDTO>> getAbsenteElevMaterie(@PathVariable Integer idElev, @PathVariable Integer idMaterie) {
        List<Absenta> absente = elevService.getAbsenteElevMaterie(idElev, idMaterie);
        return ResponseEntity.ok(absentaMapper.toDTOList(absente));
    }

    /**
     * Obtine lista burselor pentru un elev identificat prin ID.
     *
     * @param idElev ID-ul elevului.
     * @return un ResponseEntity care contine lista burselor in format DTO.
     */
    @GetMapping("/{idElev}/burse")
    public ResponseEntity<List<BursaDTO>> getBurseElev(@PathVariable int idElev) {
        List<Bursa> burse = elevService.getBurseElev(idElev);
        return ResponseEntity.ok(bursaMapper.toDTOList(burse));
    }

    /**
     * Creeaza un nou elev.
     *
     * @param elev DTO-ul elevului care trebuie creat.
     * @return un ResponseEntity care contine elevul creat in format DTO.
     */
    @PostMapping
    public ResponseEntity<ElevDTO> creareElev(@RequestBody ElevDTO elev) {
        Elev elevNou = elevService.adaugaElev(elev);
        return ResponseEntity.ok(elevMapper.toDTO(elevNou));
    }

    /**
     * Actualizeaza detaliile unui elev existent.
     *
     * @param idElev ID-ul elevului care trebuie actualizat.
     * @param elev DTO-ul cu noile detalii ale elevului.
     * @return un ResponseEntity care contine elevul actualizat in format DTO.
     */
    @PutMapping("{id}")
    public ResponseEntity<ElevDTO> actualizareElev(@PathVariable("id") Integer idElev, @RequestBody ElevDTO elev) {
        elev.setIdElev(idElev);
        Elev elevActualizat = elevService.actualizareElev(elev);
        return ResponseEntity.ok(elevMapper.toDTO(elevActualizat));
    }

    /**
     * Adauga o bursa la un elev.
     *
     * @param idElev ID-ul elevului.
     * @param idBursa ID-ul bursei.
     * @return un ResponseEntity care contine elevul actualizat in format DTO.
     */
    @PutMapping("{idElev}/burse/{idBursa}")
    public ResponseEntity<ElevDTO> adaugaBursaLaELev(@PathVariable Integer idElev, @PathVariable Integer idBursa) {
        Elev elev = elevService.adaugaBursaLaElev(idElev, idBursa);
        return ResponseEntity.ok(elevMapper.toDTO(elev));
    }

    /**
     * Sterge un elev identificat prin ID.
     *
     * @param idElev ID-ul elevului care trebuie sters.
     * @return un ResponseEntity fara continut.
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Void> stergereElev(@PathVariable("id") Integer idElev) {
        elevService.stergeElev(idElev);
        return ResponseEntity.noContent().build();
    }

    /**
     * Sterge o bursa de la un elev.
     *
     * @param idElev ID-ul elevului.
     * @param idBursa ID-ul bursei care trebuie stearsa.
     * @return un ResponseEntity fara continut.
     */
    @DeleteMapping("{idElev}/burse/{idBursa}")
    public ResponseEntity<Void> stergereBursaElev(@PathVariable Integer idElev, @PathVariable Integer idBursa) {
        elevService.stergeBursaLaElev(idElev, idBursa);
        return ResponseEntity.noContent().build();
    }

}

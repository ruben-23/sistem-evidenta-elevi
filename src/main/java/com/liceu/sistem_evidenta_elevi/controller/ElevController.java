package com.liceu.sistem_evidenta_elevi.controller;

import com.liceu.sistem_evidenta_elevi.dto.ElevDTO;
import com.liceu.sistem_evidenta_elevi.entity.Elev;
import com.liceu.sistem_evidenta_elevi.service.ElevService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("liceu/elevi")
public class ElevController {

    private ElevService elevService;

    @Autowired
    public ElevController(ElevService elevService) {
        this.elevService = elevService;
    }

    public ElevController() {}

    @GetMapping
    public ResponseEntity<List<Elev>> getAllElevi() {
        List<Elev> elevi = elevService.getAllElevi();
        return new ResponseEntity<>(elevi, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Elev> getElevById(@PathVariable("id") Integer idElev ) {
        Elev elev = elevService.getElevById(idElev);
        return new ResponseEntity<>(elev, HttpStatus.OK);
    }


    @PutMapping("{id}")
    public ResponseEntity<Elev> actualizareElev(@PathVariable("id") Integer idElev, @RequestBody ElevDTO elev) {
        elev.setIdElev(idElev);
        Elev elevActualizat = elevService.actualizareElev(elev);
        return new ResponseEntity<>(elevActualizat, HttpStatus.OK);
    }


}

package com.liceu.sistem_evidenta_elevi.service;

import com.liceu.sistem_evidenta_elevi.dto.BursaDTO;
import com.liceu.sistem_evidenta_elevi.entity.Bursa;

import java.util.List;

public interface BursaService {
    List<Bursa> getAllBurse();
    Bursa getBursaById(Integer id);
    Bursa actualizareBursa(BursaDTO bursaDTO);
    Bursa adaugaBursa(BursaDTO bursaDTO);
    void stergeBursa(Integer idBursa);
}

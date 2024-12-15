package com.liceu.sistem_evidenta_elevi.service;

import com.liceu.sistem_evidenta_elevi.dto.ElevDTO;
import com.liceu.sistem_evidenta_elevi.entity.Clasa;
import com.liceu.sistem_evidenta_elevi.entity.Elev;

import java.util.List;

public interface ElevService {
    List<Elev> getAllElevi();
    Elev getElevById(Integer id);
    Elev actualizareElev(ElevDTO elevDTO);
    Elev adaugaElev(Clasa clasa, ElevDTO elevDTO);
    void stergeElev(Integer idElev);
}

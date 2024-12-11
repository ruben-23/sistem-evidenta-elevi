package com.liceu.sistem_evidenta_elevi.service;

import com.liceu.sistem_evidenta_elevi.dto.ElevRequestDTO;
import com.liceu.sistem_evidenta_elevi.entity.Clasa;
import com.liceu.sistem_evidenta_elevi.entity.Elev;

import java.util.List;

public interface ElevService {
    List<Elev> getAllElevi();
    Elev getElevById(Integer id);
    Elev actualizareElev(ElevRequestDTO elevRequest);
    Elev adaugaElev(Clasa clasa, ElevRequestDTO elevRequest);
    void stergeElev(Integer idElev);
}

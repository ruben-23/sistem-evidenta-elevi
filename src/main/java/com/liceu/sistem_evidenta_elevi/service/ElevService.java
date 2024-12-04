package com.liceu.sistem_evidenta_elevi.service;

import com.liceu.sistem_evidenta_elevi.dto.ElevRequestDTO;
import com.liceu.sistem_evidenta_elevi.entity.Elev;

import java.util.List;

public interface ElevService {
    List<Elev> getAllElevi();
    Elev getElevById(Integer id);
    Elev actualizareElev(Elev elev);
    Elev adaugaElev(ElevRequestDTO elevRequest);
}

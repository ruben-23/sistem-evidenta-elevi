package com.liceu.sistem_evidenta_elevi.service;

import com.liceu.sistem_evidenta_elevi.dto.ElevDTO;
import com.liceu.sistem_evidenta_elevi.entity.Absenta;
import com.liceu.sistem_evidenta_elevi.entity.Clasa;
import com.liceu.sistem_evidenta_elevi.entity.Elev;
import com.liceu.sistem_evidenta_elevi.entity.Nota;

import java.util.List;

public interface ElevService {
    List<Elev> getAllElevi();
    Elev getElevById(Integer id);
    Elev actualizareElev(ElevDTO elevDTO);
    Elev adaugaElev(ElevDTO elevDTO);
    void stergeElev(Integer idElev);

    List<Nota> getNoteElevMaterie(Integer idElev, Integer idMaterie);
    List<Absenta> getAbsenteElevMaterie(Integer idElev, Integer idMaterie);

}

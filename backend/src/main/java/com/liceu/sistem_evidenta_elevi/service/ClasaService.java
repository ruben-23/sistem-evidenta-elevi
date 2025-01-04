package com.liceu.sistem_evidenta_elevi.service;

import com.liceu.sistem_evidenta_elevi.dto.*;
import com.liceu.sistem_evidenta_elevi.entity.*;

import java.util.List;

public interface ClasaService {

    List<Clasa> getAllClase();
    Clasa getClasaById(Integer id);
    Clasa adaugaClasa(ClasaDTO clasaDTO);
    Clasa actualizareClasa(ClasaDTO clasa);
    void stergeClasa(Integer idClasa);

    List<Elev> getEleviByClasa(Integer idClasa);
}

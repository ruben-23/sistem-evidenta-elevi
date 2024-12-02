package com.liceu.sistem_evidenta_elevi.service;

import com.liceu.sistem_evidenta_elevi.entity.Elev;

import java.util.List;

public interface ElevService {

    List<Elev> getAllElevi();

    Elev getElevById(int id);

    Elev actualizareElev(Elev elev);

    Elev adaugaElev(Elev elev);


}

package com.liceu.sistem_evidenta_elevi.service;

import com.liceu.sistem_evidenta_elevi.entity.Permisiune;

import java.util.List;

public interface PermisiuneService {

    Permisiune getPermisiuneById(Integer id);
    List<Permisiune> getAllPermisiuni();
    Permisiune adaugaPermisiune(Permisiune Permisiune);
    Permisiune actualizeazaPermisiune(Permisiune Permisiune);
    
}

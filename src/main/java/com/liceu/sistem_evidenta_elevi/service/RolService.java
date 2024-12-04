package com.liceu.sistem_evidenta_elevi.service;

import com.liceu.sistem_evidenta_elevi.entity.Rol;

import java.util.List;

public interface RolService {
    Rol getRolByNume(String nume);
    List<Rol> getAllRoluri();
    Rol adaugaRol(Rol rol);
    Rol actualizeazaRol(Rol rol);
}

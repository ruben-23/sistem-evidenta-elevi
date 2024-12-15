package com.liceu.sistem_evidenta_elevi.service;

import com.liceu.sistem_evidenta_elevi.dto.AbsentaDTO;
import com.liceu.sistem_evidenta_elevi.entity.Absenta;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AbsentaService {

    List<Absenta> getAllAbsente();
    Absenta getAbsentaById(Integer id);
    Absenta actualizareAbsenta(AbsentaDTO absentaDTO);
    Absenta adaugaAbsenta(AbsentaDTO absentaDTO);
    void stergeAbsenta(Integer idAbsenta);


}

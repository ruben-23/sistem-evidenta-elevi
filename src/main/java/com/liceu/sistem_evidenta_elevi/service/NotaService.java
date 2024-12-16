package com.liceu.sistem_evidenta_elevi.service;

import com.liceu.sistem_evidenta_elevi.dto.NotaDTO;
import com.liceu.sistem_evidenta_elevi.entity.Nota;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NotaService {
    List<Nota> getAllNote();
    Nota getNotaById(Integer id);
    Nota actualizareNota(NotaDTO notaDTO);
    Nota adaugaNota(NotaDTO notaDTO);
    void stergeNota(Integer idNota);
}

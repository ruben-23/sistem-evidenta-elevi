package com.liceu.sistem_evidenta_elevi.service.implementare;

import com.liceu.sistem_evidenta_elevi.dto.NotaDTO;

import com.liceu.sistem_evidenta_elevi.entity.Nota;
import com.liceu.sistem_evidenta_elevi.mapper.NotaMapper;
import com.liceu.sistem_evidenta_elevi.repository.NotaRepository;
import com.liceu.sistem_evidenta_elevi.service.NotaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotaServiceImplementare implements NotaService {

    private final NotaRepository notaRepository;
    private final NotaMapper notaMapper;

    @Autowired
    public NotaServiceImplementare(NotaRepository notaRepository, NotaMapper notaMapper) {
        this.notaRepository = notaRepository;
        this.notaMapper = notaMapper;
    }

    @Override
    public List<Nota> getAllNote(){
        return notaRepository.findAll();
    }

    @Override
    public Nota getNotaById(Integer id){
        return notaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nota nu a fost gasita"));
    }

    @Transactional
    @Override
    public Nota actualizareNota(NotaDTO notaDTO){
        Nota notaActuala = getNotaById(notaDTO.getIdNota());
        notaMapper.updateEntityFromDTO(notaDTO, notaActuala);
        return notaRepository.save(notaActuala);
    }

    @Transactional
    @Override
    public Nota adaugaNota(NotaDTO notaDTO){
        Nota nota = notaMapper.toEntity(notaDTO);
        return notaRepository.save(nota);
    }

    @Override
    public void stergeNota(Integer idNota) {
        notaRepository.deleteById(idNota);
    }

}

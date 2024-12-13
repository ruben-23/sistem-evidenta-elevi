package com.liceu.sistem_evidenta_elevi.service.implementare;

import com.liceu.sistem_evidenta_elevi.dto.NotaRequestDTO;
import com.liceu.sistem_evidenta_elevi.entity.Elev;
import com.liceu.sistem_evidenta_elevi.entity.Materie;
import com.liceu.sistem_evidenta_elevi.entity.Nota;
import com.liceu.sistem_evidenta_elevi.repository.NotaRepository;
import com.liceu.sistem_evidenta_elevi.service.ElevService;
import com.liceu.sistem_evidenta_elevi.service.MaterieService;
import com.liceu.sistem_evidenta_elevi.service.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotaServiceImplementare implements NotaService {

    private final NotaRepository notaRepository;
    private final ElevService elevService;
    private final MaterieService materieService;
    private NotaRepository repository;

    @Autowired
    public NotaServiceImplementare(NotaRepository repository, NotaRepository notaRepository, ElevService elevService, MaterieService materieService) {
        this.repository = repository;
        this.notaRepository = notaRepository;
        this.elevService = elevService;
        this.materieService = materieService;
    }

    @Override
    public List<Nota> getAllNote(){
        return repository.findAll();
    }

    @Override
    public Nota getNotaById(Integer id){
        return notaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nota nu a fost gasita"));
    }

    @Override
    public Nota actualizareNota(NotaRequestDTO notaRequest){
        Nota notaActuala = getNotaById(notaRequest.getIdNota());
        notaActuala.setData(notaRequest.getData());
        notaActuala.setValoare(notaRequest.getValoare());
        return notaRepository.save(notaActuala);
    }

    @Override
    public Nota adaugaNota(NotaRequestDTO notaRequest){

        Elev elev = elevService.getElevById(notaRequest.getIdElev());
        Materie materie = materieService.getMaterieById(notaRequest.getIdMaterie());

        Nota nota = new Nota();
        nota.setData(notaRequest.getData());
        nota.setValoare(notaRequest.getValoare());
        nota.setMaterie(materie);
        nota.setElev(elev);
        return notaRepository.save(nota);
    }

    @Override
    public void stergeNota(Integer idNota) {
        notaRepository.delete(getNotaById(idNota));
    }

}

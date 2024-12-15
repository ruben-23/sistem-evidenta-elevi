package com.liceu.sistem_evidenta_elevi.service.implementare;

import com.liceu.sistem_evidenta_elevi.dto.AbsentaDTO;
import com.liceu.sistem_evidenta_elevi.entity.Absenta;
import com.liceu.sistem_evidenta_elevi.entity.Elev;
import com.liceu.sistem_evidenta_elevi.entity.Materie;
import com.liceu.sistem_evidenta_elevi.repository.AbsentaRepository;
import com.liceu.sistem_evidenta_elevi.service.AbsentaService;
import com.liceu.sistem_evidenta_elevi.service.ElevService;
import com.liceu.sistem_evidenta_elevi.service.MaterieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbsentaServiceImplementare implements AbsentaService {

    private final ElevService elevService;
    private final MaterieService materieService;
    private final AbsentaRepository absentaRepository;

    @Autowired
    public AbsentaServiceImplementare(AbsentaRepository absentaRepository, ElevService elevService, MaterieService materieService) {
        this.absentaRepository = absentaRepository;
        this.elevService = elevService;
        this.materieService = materieService;
    }

    @Override
    public List<Absenta> getAllAbsente(){
        return absentaRepository.findAll();
    }

    @Override
    public Absenta getAbsentaById(Integer id){
        return absentaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Absenta nu a fost gasita"));
    }

    @Override
    public Absenta actualizareAbsenta(AbsentaDTO afsentaDTO){
        Absenta absentaActuala = getAbsentaById(afsentaDTO.getIdAbsenta());
        absentaActuala.setData(afsentaDTO.getData());
        return absentaRepository.save(absentaActuala);
    }

    @Override
    public Absenta adaugaAbsenta(AbsentaDTO afsentaDTO){
        Elev elev = elevService.getElevById(afsentaDTO.getIdElev());
        Materie materie = materieService.getMaterieById(afsentaDTO.getIdMaterie());

        Absenta absenta = new Absenta();
        absenta.setData(afsentaDTO.getData());
        absenta.setElev(elev);
        absenta.setMaterie(materie);
        return absentaRepository.save(absenta);
    }

    @Override
    public void stergeAbsenta(Integer idAbsenta) {
        absentaRepository.delete(getAbsentaById(idAbsenta));
    }

}

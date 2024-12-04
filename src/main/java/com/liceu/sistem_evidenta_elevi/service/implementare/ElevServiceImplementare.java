package com.liceu.sistem_evidenta_elevi.service.implementare;

import com.liceu.sistem_evidenta_elevi.dto.ElevRequestDTO;
import com.liceu.sistem_evidenta_elevi.entity.Elev;
import com.liceu.sistem_evidenta_elevi.repository.ElevRepository;
import com.liceu.sistem_evidenta_elevi.service.ElevService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ElevServiceImplementare implements ElevService {

    private ElevRepository elevRepository;

    public ElevServiceImplementare() {}

    @Autowired
    public ElevServiceImplementare(ElevRepository elevRepository) {
        this.elevRepository = elevRepository;
    }

    @Override
    public List<Elev> getAllElevi(){
        return elevRepository.findAll();
    }

    @Override
    public Elev getElevById(Integer id){
        Optional<Elev> elev = elevRepository.findById(id);
        return elev.get();
    }

    @Override
    public Elev actualizareElev(Elev elev){
        Elev elevActual = elevRepository.findById(elev.getIdElev()).get();
        elevActual.setNume(elev.getNume());
        elevActual.setPrenume(elev.getPrenume());
        elevActual.setCNP(elev.getCNP());
        elevActual.setSex(elev.getSex());
        elevActual.setNumarTelefon(elev.getNumarTelefon());
        elevActual.setAdresa(elev.getAdresa());
        elevActual.setDataNasterii(elev.getDataNasterii());

        return elevRepository.save(elevActual);
    }

    @Override
    public Elev adaugaElev(ElevRequestDTO elevRequestDTO){

        Elev elev = new Elev();
        elev.setNume(elevRequestDTO.getNume());
        elev.setPrenume(elevRequestDTO.getPrenume());
        elev.setCNP(elevRequestDTO.getCNP());
        elev.setNumarTelefon(elevRequestDTO.getNumarTelefon());
        elev.setAdresa(elevRequestDTO.getAdresa());
        elev.setDataNasterii(elevRequestDTO.getDataNasterii());
        /* TODO: legatura cu clasa */

        return elevRepository.save(elev);
    }

}

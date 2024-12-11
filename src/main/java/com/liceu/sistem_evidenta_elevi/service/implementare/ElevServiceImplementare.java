package com.liceu.sistem_evidenta_elevi.service.implementare;

import com.liceu.sistem_evidenta_elevi.dto.ElevRequestDTO;
import com.liceu.sistem_evidenta_elevi.entity.Clasa;
import com.liceu.sistem_evidenta_elevi.entity.Elev;
import com.liceu.sistem_evidenta_elevi.repository.ElevRepository;
import com.liceu.sistem_evidenta_elevi.service.ClasaService;
import com.liceu.sistem_evidenta_elevi.service.ElevService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        return elevRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Elevul nu a fost gasit"));
    }

    @Override
    public Elev actualizareElev(ElevRequestDTO elevRequest){
        Elev elevActual = getElevById(elevRequest.getIdElev());
        elevActual.setNume(elevRequest.getNume());
        elevActual.setPrenume(elevRequest.getPrenume());
        elevActual.setCNP(elevRequest.getCNP());
        elevActual.setSex(elevRequest.getSex());
        elevActual.setNumarTelefon(elevRequest.getNumarTelefon());
        elevActual.setAdresa(elevRequest.getAdresa());
        elevActual.setDataNasterii(elevRequest.getDataNasterii());

        return elevRepository.save(elevActual);
    }

    @Override
    public Elev adaugaElev(Clasa clasa, ElevRequestDTO elevRequestDTO){

        // creare elev
        Elev elev = new Elev();
        elev.setNume(elevRequestDTO.getNume());
        elev.setPrenume(elevRequestDTO.getPrenume());
        elev.setCNP(elevRequestDTO.getCNP());
        elev.setNumarTelefon(elevRequestDTO.getNumarTelefon());
        elev.setAdresa(elevRequestDTO.getAdresa());
        elev.setDataNasterii(elevRequestDTO.getDataNasterii());

        // creare legatura cu clasa din care face parte
        elev.setClasa(clasa);

        return elevRepository.save(elev);
    }

}

package com.liceu.sistem_evidenta_elevi.service.implementare;

import com.liceu.sistem_evidenta_elevi.dto.ElevDTO;
import com.liceu.sistem_evidenta_elevi.entity.Absenta;
import com.liceu.sistem_evidenta_elevi.entity.Bursa;
import com.liceu.sistem_evidenta_elevi.entity.Elev;
import com.liceu.sistem_evidenta_elevi.entity.Nota;
import com.liceu.sistem_evidenta_elevi.mapper.ElevMapper;
import com.liceu.sistem_evidenta_elevi.repository.ElevRepository;
import com.liceu.sistem_evidenta_elevi.service.BursaService;
import com.liceu.sistem_evidenta_elevi.service.ElevService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementare a serviciului Elev.
 * Contine metode pentru gestionarea operatiunilor legate de elevi.
 */
@Service
public class ElevServiceImplementare implements ElevService {

    private final ElevRepository elevRepository;
    private final ElevMapper elevMapper;
    private final BursaService bursaService;

    /**
     * Constructor pentru injectarea dependintelor.
     *
     * @param elevRepository Repositorul pentru gestionarea operatiunilor cu elevi.
     * @param elevMapper     Mapper-ul pentru conversia intre Elev si ElevDTO.
     * @param bursaService   Serviciul pentru gestionarea burselor.
     */
    @Autowired
    public ElevServiceImplementare(ElevRepository elevRepository, ElevMapper elevMapper,
                                   BursaService bursaService) {
        this.elevRepository = elevRepository;
        this.elevMapper = elevMapper;
        this.bursaService = bursaService;
    }

    /**
     * Obtine toti elevii.
     *
     * @return Lista tuturor elevilor.
     */
    @Override
    public List<Elev> getAllElevi(){
        return elevRepository.findAll();
    }

    /**
     * Obtine un elev dupa ID.
     *
     * @param id ID-ul elevului.
     * @return Elevul corespunzator ID-ului.
     * @throws RuntimeException Daca elevul nu este gasit.
     */
    @Override
    public Elev getElevById(Integer id){
        return elevRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Elevul nu a fost gasit"));
    }

    /**
     * Actualizeaza un elev pe baza unui ElevDTO.
     *
     * @param elevDTO DTO-ul care contine noile date pentru elev.
     * @return Elevul actualizat.
     */
    @Transactional
    @Override
    public Elev actualizareElev(ElevDTO elevDTO){
        Elev elevActual = getElevById(elevDTO.getIdElev());
        elevMapper.updateEntityFromDTO(elevDTO, elevActual);
        return elevRepository.save(elevActual);
    }

    /**
     * Adauga un elev in sistem pe baza unui ElevDTO.
     *
     * @param elevDTO DTO-ul care contine datele pentru elevul nou.
     * @return Elevul adaugat.
     */
    @Transactional
    @Override
    public Elev adaugaElev(ElevDTO elevDTO){
        Elev elev = elevMapper.toEntity(elevDTO);
        return elevRepository.save(elev);
    }

    /**
     * Sterge un elev din sistem pe baza ID-ului.
     *
     * @param idElev ID-ul elevului de sters.
     */
    @Transactional
    @Override
    public void stergeElev(Integer idElev){
        elevRepository.deleteByIdElev(idElev);
    }

    /**
     * Obtine lista notelor unui elev.
     *
     * @param idElev ID-ul elevului.
     * @return Lista notelor elevului.
     */
    @Override
    public List<Nota> getNoteElev(Integer idElev){
        Elev elev = getElevById(idElev);
        return elev.getNote();
    }

    /**
     * Obtine lista absentelor unui elev.
     *
     * @param idElev ID-ul elevului.
     * @return Lista absentelor elevului.
     */
    @Override
    public List<Absenta> getAbsenteElev(Integer idElev){
        Elev elev = getElevById(idElev);
        return elev.getAbsente();
    }

    /**
     * Obtine notele unui elev pentru o anumita materie.
     *
     * @param idElev   ID-ul elevului.
     * @param idMaterie ID-ul materiei.
     * @return Lista notelor pentru materia specificata.
     */
    @Override
    public List<Nota> getNoteElevMaterie(Integer idElev, Integer idMaterie){
        Elev elev = getElevById(idElev);
        return elev.getNote().stream()
                .filter(nota -> nota.getMaterie().getIdMaterie().equals(idMaterie))
                .collect(Collectors.toList());
    }

    /**
     * Obtine absentelor unui elev pentru o anumita materie.
     *
     * @param idElev   ID-ul elevului.
     * @param idMaterie ID-ul materiei.
     * @return Lista absentelor pentru materia specificata.
     */
    @Override
    public List<Absenta> getAbsenteElevMaterie(Integer idElev, Integer idMaterie){
        Elev elev = getElevById(idElev);
        return elev.getAbsente()
                .stream()
                .filter(absenta -> absenta.getMaterie().getIdMaterie().equals(idMaterie))
                .collect(Collectors.toList());
    }

    /**
     * Obtine lista burselor unui elev.
     *
     * @param idElev ID-ul elevului.
     * @return Lista burselor elevului.
     */
    @Override
    public List<Bursa> getBurseElev(Integer idElev){
        Elev elev = getElevById(idElev);
        return elev.getBurse();
    }

    /**
     * Adauga o bursa unui elev.
     *
     * @param idElev ID-ul elevului.
     * @param idBursa ID-ul bursei.
     * @return Elevul actualizat cu bursa adaugata.
     */
    @Transactional
    @Override
    public Elev adaugaBursaLaElev(Integer idElev, Integer idBursa){
        Elev elev = getElevById(idElev);
        Bursa bursa = bursaService.getBursaById(idBursa);

        elev.getBurse().add(bursa);
        return elevRepository.save(elev);
    }

    /**
     * Sterge o bursa de la un elev.
     *
     * @param idElev ID-ul elevului.
     * @param idBursa ID-ul bursei de sters.
     */
    @Transactional
    @Override
    public void stergeBursaLaElev(Integer idElev, Integer idBursa){
        Elev elev = getElevById(idElev);
        Bursa bursa = bursaService.getBursaById(idBursa);
        elev.getBurse().remove(bursa);
        elevRepository.save(elev);
    }
}
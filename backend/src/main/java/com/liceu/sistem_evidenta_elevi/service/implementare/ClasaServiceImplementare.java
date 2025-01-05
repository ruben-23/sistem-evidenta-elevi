package com.liceu.sistem_evidenta_elevi.service.implementare;

import com.liceu.sistem_evidenta_elevi.dto.ClasaDTO;
import com.liceu.sistem_evidenta_elevi.entity.*;
import com.liceu.sistem_evidenta_elevi.mapper.ClasaMapper;
import com.liceu.sistem_evidenta_elevi.repository.ClasaRepository;
import com.liceu.sistem_evidenta_elevi.service.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementare a serviciului Clasa.
 * Contine metode pentru gestionarea operatiunilor legate de clase.
 */
@Service
public class ClasaServiceImplementare implements ClasaService {

    private final ClasaRepository clasaRepository;
    private final ClasaMapper clasaMapper;

    /**
     * Constructor pentru injectarea dependintelor.
     *
     * @param clasaRepository Repositorul pentru gestionarea operatiunilor cu clase.
     * @param clasaMapper     Mapper-ul pentru conversia intre Clasa si ClasaDTO.
     */
    @Autowired
    public ClasaServiceImplementare(ClasaRepository clasaRepository, ClasaMapper clasaMapper) {
        this.clasaRepository = clasaRepository;
        this.clasaMapper = clasaMapper;
    }

    /**
     * Obtine toate clasele.
     *
     * @return Lista tuturor claselor.
     */
    @Override
    public List<Clasa> getAllClase(){
        return clasaRepository.findAll();
    }

    /**
     * Obtine o clasa dupa ID.
     *
     * @param id ID-ul clasei.
     * @return Clasa corespunzatoare ID-ului.
     * @throws RuntimeException Daca clasa nu este gasita.
     */
    @Override
    public Clasa getClasaById(Integer id){
        return clasaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Clasa nu a fost gasita"));
    }

    /**
     * Actualizeaza o clasa pe baza unui ClasaDTO.
     *
     * @param clasaDTO DTO-ul care contine noile date pentru clasa.
     * @return Clasa actualizata.
     */
    @Transactional
    @Override
    public Clasa actualizareClasa(ClasaDTO clasaDTO){
        Clasa clasaActuala = getClasaById(clasaDTO.getIdClasa());
        clasaMapper.updateEntityFromDTO(clasaDTO, clasaActuala);
        return clasaRepository.save(clasaActuala);
    }

    /**
     * Adauga o clasa in sistem pe baza unui ClasaDTO.
     *
     * @param clasaDTO DTO-ul care contine datele pentru clasa noua.
     * @return Clasa adaugata.
     */
    @Transactional
    @Override
    public Clasa adaugaClasa(ClasaDTO clasaDTO){
        Clasa clasa = clasaMapper.toEntity(clasaDTO);
        return clasaRepository.save(clasa);
    }

    /**
     * Sterge o clasa din sistem pe baza ID-ului.
     *
     * @param idClasa ID-ul clasei de sters.
     */
    @Override
    public void stergeClasa(Integer idClasa){
        clasaRepository.delete(getClasaById(idClasa));
    }

    /**
     * Obtine lista elevilor dintr-o clasa.
     *
     * @param clasaId ID-ul clasei.
     * @return Lista elevilor din clasa respectiva.
     */
    @Override
    public List<Elev> getEleviByClasa(Integer clasaId) {
        Clasa clasa = getClasaById(clasaId);
        return clasa.getElevi();
    }
}

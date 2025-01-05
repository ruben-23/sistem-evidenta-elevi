package com.liceu.sistem_evidenta_elevi.service.implementare;

import com.liceu.sistem_evidenta_elevi.dto.MaterieDTO;
import com.liceu.sistem_evidenta_elevi.entity.Materie;
import com.liceu.sistem_evidenta_elevi.mapper.MaterieMapper;
import com.liceu.sistem_evidenta_elevi.repository.MaterieRepository;
import com.liceu.sistem_evidenta_elevi.service.MaterieService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementare a serviciului Materie.
 * Contine metode pentru gestionarea operatiunilor legate de materii.
 */
@Service
public class MaterieServiceImplementare implements MaterieService {

    private final MaterieRepository materieRepository;
    private final MaterieMapper materieMapper;

    /**
     * Constructor pentru injectarea dependintelor.
     *
     * @param materieRepository Repositorul pentru gestionarea operatiunilor cu materii.
     * @param materieMapper     Mapper-ul pentru conversia intre Materie si MaterieDTO.
     */
    @Autowired
    public MaterieServiceImplementare(MaterieRepository materieRepository, MaterieMapper materieMapper) {
        this.materieRepository = materieRepository;
        this.materieMapper = materieMapper;
    }

    /**
     * Obtine toate materiile.
     *
     * @return Lista tuturor materiilor.
     */
    @Override
    public List<Materie> getAllMaterii(){
        return materieRepository.findAll();
    }

    /**
     * Obtine o materie dupa ID.
     *
     * @param id ID-ul materiei.
     * @return Materia corespunzatoare ID-ului.
     * @throws RuntimeException Daca materia nu este gasita.
     */
    @Override
    public Materie getMaterieById(Integer id){
        return materieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Materia nu a fost gasita"));
    }

    /**
     * Actualizeaza o materie pe baza unui MaterieDTO.
     *
     * @param materieDTO DTO-ul care contine noile date pentru materie.
     * @return Materia actualizata.
     */
    @Transactional
    @Override
    public Materie actualizareMaterie(MaterieDTO materieDTO){
        Materie materieActuala = getMaterieById(materieDTO.getIdMaterie());
        materieMapper.updateEntityFromDTO(materieDTO, materieActuala);
        return materieRepository.save(materieActuala);
    }

    /**
     * Adauga o materie in sistem pe baza unui MaterieDTO.
     *
     * @param materieDTO DTO-ul care contine datele pentru materie.
     * @return Materia adaugata.
     */
    @Transactional
    @Override
    public Materie adaugaMaterie(MaterieDTO materieDTO){
        Materie materie = materieMapper.toEntity(materieDTO);
        return materieRepository.save(materie);
    }

    /**
     * Sterge o materie din sistem pe baza ID-ului.
     *
     * @param idMaterie ID-ul materiei de sters.
     */
    @Override
    public void stergeMaterie(Integer idMaterie){
        materieRepository.deleteById(idMaterie);
    }
}
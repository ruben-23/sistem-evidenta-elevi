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

/**
 * Implementare a serviciului Nota.
 * Contine metode pentru gestionarea operatiunilor legate de note.
 */
@Service
public class NotaServiceImplementare implements NotaService {

    private final NotaRepository notaRepository;
    private final NotaMapper notaMapper;

    /**
     * Constructor pentru injectarea dependintelor.
     *
     * @param notaRepository Repositorul pentru gestionarea operatiunilor cu note.
     * @param notaMapper     Mapper-ul pentru conversia intre Nota si NotaDTO.
     */
    @Autowired
    public NotaServiceImplementare(NotaRepository notaRepository, NotaMapper notaMapper) {
        this.notaRepository = notaRepository;
        this.notaMapper = notaMapper;
    }

    /**
     * Obtine toate notele.
     *
     * @return Lista tuturor notelor.
     */
    @Override
    public List<Nota> getAllNote(){
        return notaRepository.findAll();
    }

    /**
     * Obtine o nota dupa ID.
     *
     * @param id ID-ul notei.
     * @return Nota corespunzatoare ID-ului.
     * @throws RuntimeException Daca nota nu este gasita.
     */
    @Override
    public Nota getNotaById(Integer id){
        return notaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nota nu a fost gasita"));
    }

    /**
     * Actualizeaza o nota pe baza unui NotaDTO.
     *
     * @param notaDTO DTO-ul care contine noile date pentru nota.
     * @return Nota actualizata.
     */
    @Transactional
    @Override
    public Nota actualizareNota(NotaDTO notaDTO){
        Nota notaActuala = getNotaById(notaDTO.getIdNota());
        notaMapper.updateEntityFromDTO(notaDTO, notaActuala);
        return notaRepository.save(notaActuala);
    }

    /**
     * Adauga o nota in sistem pe baza unui NotaDTO.
     *
     * @param notaDTO DTO-ul care contine datele pentru nota.
     * @return Nota adaugata.
     */
    @Transactional
    @Override
    public Nota adaugaNota(NotaDTO notaDTO){
        Nota nota = notaMapper.toEntity(notaDTO);
        return notaRepository.save(nota);
    }

    /**
     * Sterge o nota din sistem pe baza ID-ului.
     *
     * @param idNota ID-ul notei de sters.
     */
    @Override
    public void stergeNota(Integer idNota) {
        notaRepository.deleteById(idNota);
    }
}

package com.liceu.sistem_evidenta_elevi.repository;

import com.liceu.sistem_evidenta_elevi.entity.Elev;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repository pentru entitatea Elev.
 * Aceasta interfata extinde JpaRepository si furnizeaza operatiuni standard de CRUD pentru entitatea Elev.
 * De asemenea, contine o metoda personalizata pentru stergerea unui elev dupa ID.
 */
@Repository
public interface ElevRepository extends JpaRepository<Elev, Integer> {

    /**
     * Sterge un elev din baza de date pe baza ID-ului.
     *
     * @param idElev ID-ul elevului care trebuie sters.
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM Elev e WHERE e.idElev = :idElev")
    void deleteByIdElev(@Param("idElev") Integer idElev);
}
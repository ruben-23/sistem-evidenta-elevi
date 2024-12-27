package com.liceu.sistem_evidenta_elevi.repository;

import com.liceu.sistem_evidenta_elevi.entity.Elev;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ElevRepository extends JpaRepository<Elev, Integer> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Elev e WHERE e.idElev = :idElev")
    void deleteByIdElev(@Param("idElev") Integer idElev);

}

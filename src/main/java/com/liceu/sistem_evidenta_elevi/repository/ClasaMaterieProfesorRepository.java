package com.liceu.sistem_evidenta_elevi.repository;


import com.liceu.sistem_evidenta_elevi.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClasaMaterieProfesorRepository extends JpaRepository<ClasaMaterieProfesor, ClasaMaterieProfesorId> {

    // returnare materii predate intr-o clasa
    @Query("SELECT cmp.materie FROM ClasaMaterieProfesor cmp WHERE cmp.clasa.idClasa = :idClasa")
    List<Materie> findMateriiByClasaId(@Param("idClasa") Integer idClasa);

    // returnare materii predate de un profesor pentru o anumita clasa
    @Query("SELECT cmp.materie FROM ClasaMaterieProfesor cmp WHERE cmp.profesor.idProfesor = :idProfesor AND cmp.clasa.idClasa = :idClasa")
    List<Materie> findMateriiByProfesorAndClasa(@Param("idProfesor") Integer idProfesor, @Param("idClasa") Integer idClasa);

    // returnare clase in care preda un anumit profesor
    @Query("SELECT DISTINCT cmp.clasa FROM ClasaMaterieProfesor cmp WHERE cmp.profesor.idProfesor = :idProfesor")
    List<Clasa> findClaseByProfesorId(@Param("idProfesor") Integer idProfesor);

    // returnare profesori care care predau intr-o anumita clasa
    @Query("SELECT DISTINCT cmp.profesor FROM ClasaMaterieProfesor cmp WHERE cmp.clasa.idClasa = :idClasa")
    List<Profesor> findProfesoriByClasaId(@Param("idClasa") Integer idClasa);

}
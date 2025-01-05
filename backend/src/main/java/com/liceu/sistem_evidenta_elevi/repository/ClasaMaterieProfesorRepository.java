package com.liceu.sistem_evidenta_elevi.repository;

import com.liceu.sistem_evidenta_elevi.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository pentru entitatea ClasaMaterieProfesor.
 * Aceasta interfata extinde JpaRepository si furnizeaza operatiuni pentru gestionarea relatiilor
 * dintre clase, materii si profesori. Aceasta include interogari personalizate pentru a obtine materii,
 * profesori si clase in functie de diferite criterii.
 */
public interface ClasaMaterieProfesorRepository extends JpaRepository<ClasaMaterieProfesor, ClasaMaterieProfesorId> {

    /**
     * Returneaza lista de materii predate intr-o anumita clasa.
     *
     * @param idClasa ID-ul clasei pentru care se cauta materiile.
     * @return Lista de obiecte Materie asociate cu clasa specificata.
     */
    @Query("SELECT cmp.materie FROM ClasaMaterieProfesor cmp WHERE cmp.clasa.idClasa = :idClasa")
    List<Materie> findMateriiByIdClasa(@Param("idClasa") Integer idClasa);

    /**
     * Returneaza lista de materii predate de un anumit profesor intr-o anumita clasa.
     *
     * @param idProfesor ID-ul profesorului.
     * @param idClasa ID-ul clasei.
     * @return Lista de obiecte Materie asociate cu profesorul si clasa specificate.
     */
    @Query("SELECT cmp.materie FROM ClasaMaterieProfesor cmp WHERE cmp.profesor.idProfesor = :idProfesor AND cmp.clasa.idClasa = :idClasa")
    List<Materie> findMateriiByProfesorAndClasa(@Param("idProfesor") Integer idProfesor, @Param("idClasa") Integer idClasa);

    /**
     * Returneaza lista de clase in care preda un anumit profesor.
     *
     * @param idProfesor ID-ul profesorului pentru care se cauta clasele.
     * @return Lista de obiecte Clasa asociate cu profesorul specificat.
     */
    @Query("SELECT DISTINCT cmp.clasa FROM ClasaMaterieProfesor cmp WHERE cmp.profesor.idProfesor = :idProfesor")
    List<Clasa> findClaseByProfesorId(@Param("idProfesor") Integer idProfesor);

    /**
     * Returneaza lista de profesori care predau intr-o anumita clasa.
     *
     * @param idClasa ID-ul clasei pentru care se cauta profesorii.
     * @return Lista de obiecte Profesor asociate cu clasa specificata.
     */
    @Query("SELECT DISTINCT cmp.profesor FROM ClasaMaterieProfesor cmp WHERE cmp.clasa.idClasa = :idClasa")
    List<Profesor> findProfesoriByClasaId(@Param("idClasa") Integer idClasa);
}

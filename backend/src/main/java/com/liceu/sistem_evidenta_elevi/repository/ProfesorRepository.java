package com.liceu.sistem_evidenta_elevi.repository;

import com.liceu.sistem_evidenta_elevi.entity.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository pentru entitatea Profesor.
 * Aceasta interfata extinde JpaRepository si furnizeaza operatiuni standard de CRUD pentru entitatea Profesor.
 */
public interface ProfesorRepository extends JpaRepository<Profesor, Integer> {
}
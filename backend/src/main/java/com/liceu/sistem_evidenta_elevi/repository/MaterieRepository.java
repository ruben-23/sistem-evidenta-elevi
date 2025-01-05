package com.liceu.sistem_evidenta_elevi.repository;

import com.liceu.sistem_evidenta_elevi.entity.Materie;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository pentru entitatea Materie.
 *
 * Aceasta interfata extinde JpaRepository si furnizeaza operatiuni standard de CRUD pentru entitatea Materie.
 */
public interface MaterieRepository extends JpaRepository<Materie, Integer> {
}

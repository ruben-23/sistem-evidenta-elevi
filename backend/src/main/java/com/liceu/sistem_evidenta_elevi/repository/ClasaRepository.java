package com.liceu.sistem_evidenta_elevi.repository;

import com.liceu.sistem_evidenta_elevi.entity.Clasa;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository pentru entitatea Clasa.
 *
 * Aceasta interfata extinde JpaRepository si furnizeaza operatiuni standard de CRUD pentru entitatea Clasa.
 */
public interface ClasaRepository extends JpaRepository<Clasa, Integer> {
}

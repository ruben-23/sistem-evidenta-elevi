package com.liceu.sistem_evidenta_elevi.repository;

import com.liceu.sistem_evidenta_elevi.entity.Nota;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository pentru entitatea Nota.
 * Aceasta interfata extinde JpaRepository si furnizeaza operatiuni standard de CRUD pentru entitatea Nota.
 */
public interface NotaRepository extends JpaRepository<Nota, Integer> {
}
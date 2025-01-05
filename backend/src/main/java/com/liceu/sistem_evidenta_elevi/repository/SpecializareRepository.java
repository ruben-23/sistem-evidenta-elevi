package com.liceu.sistem_evidenta_elevi.repository;

import com.liceu.sistem_evidenta_elevi.entity.Specializare;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository pentru entitatea Specializare.
 * Aceasta interfata extinde JpaRepository si furnizeaza operatiuni standard de CRUD pentru entitatea Specializare.
 */
public interface SpecializareRepository extends JpaRepository<Specializare, Integer> {
}
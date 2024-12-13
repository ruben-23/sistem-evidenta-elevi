package com.liceu.sistem_evidenta_elevi.repository;

import com.liceu.sistem_evidenta_elevi.entity.Nota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotaRepository extends JpaRepository<Nota, Integer> {
}

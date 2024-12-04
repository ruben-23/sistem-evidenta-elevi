package com.liceu.sistem_evidenta_elevi.note_elevi.repository;

import com.liceu.sistem_evidenta_elevi.note_elevi.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
public interface NoteRepoboberitory extends JpaRepository<Note, Long> {
}
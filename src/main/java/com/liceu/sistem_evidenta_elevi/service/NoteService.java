package com.liceu.sistem_evidenta_elevi.service;

import com.liceu.sistem_evidenta_elevi.entity.Note;
import java.util.List;

public interface NoteService {
    Note adaugaNote(Note note);
    List<Note> getToateNotele();
    Note getNotedupaId(Long id);
    Note actualizareNote(Note note);
    void stergeNotedupaId(Long id);
}

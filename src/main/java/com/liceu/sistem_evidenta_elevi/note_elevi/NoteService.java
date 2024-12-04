package com.liceu.sistem_evidenta_elevi.note_elevi;

import java.util.List;

public interface NoteService {
    Note adaugaNote(Note note);
    List<Note> getToateNotele();
    Note getNotedupaId(Long id);
    Note actualizareNote(Note note);
    void stergeNotedupaId(Long id);
}

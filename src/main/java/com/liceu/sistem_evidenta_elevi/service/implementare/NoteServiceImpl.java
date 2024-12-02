package com.liceu.sistem_evidenta_elevi.service.implementare;

import com.liceu.sistem_evidenta_elevi.entity.Note;
import com.liceu.sistem_evidenta_elevi.repository.NoteRepoboberitory;
import com.liceu.sistem_evidenta_elevi.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteServiceImpl implements NoteService {

    private NoteRepoboberitory NoteRepoboberitory;

    @Autowired
    public NoteServiceImpl(NoteRepoboberitory NoteRepoboberitory) {
        this.NoteRepoboberitory = NoteRepoboberitory;
    }

    @Override
    public Note adaugaNote(Note note) {
        return NoteRepoboberitory.save(note);
    }

    @Override
    public List<Note> getToateNotele() {
        return NoteRepoboberitory.findAll();
    }

    @Override
    public Note getNotedupaId(Long id) {
        Optional<Note> note = NoteRepoboberitory.findById(id);
        if (note.isPresent()) {
            return note.get();
        } else {
            throw new IllegalArgumentException("Nota nu a fost gasita cu id-ul: " + id);
        }
    }

    @Override
    public Note actualizareNote(Note note) {
        if (NoteRepoboberitory.existsById(note.getId())) {
            return NoteRepoboberitory.save(note);
        } else {
            throw new IllegalArgumentException("Nota nu a fost gasita cu id-ul: " + note.getId());
        }
    }

    @Override
    public void stergeNotedupaId(Long id) {
        if (NoteRepoboberitory.existsById(id)) {
            NoteRepoboberitory.deleteById(id);
        } else {
            throw new IllegalArgumentException("Nota nu a fost gasita cu id-ul: " + id);
        }
    }
}

package com.javaspring.server.services.impl;

import com.javaspring.server.model.Note;
import com.javaspring.server.repositories.NoteRepository;
import com.javaspring.server.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class NoteServiceimpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Override
    public Note createNoteForUser(String username,String content){
        Note note = new Note();
        note.setContent(content);
        note.setOwnerUsername(username);
        Note savedNote = noteRepository.save(note);
        return savedNote;
    }

    @Override
    public Note updateNoteForUser(Long noteId, String content,
                                  String username){
        Note note = noteRepository
                .findById(Math.toIntExact(noteId))
                .orElseThrow(()->new RuntimeException("Note not found!!!"));
        note.setContent(content);
        Note updatedNote = noteRepository.save(note);
        return updatedNote;

    }

    @Override
    public void deleteNoteForUser(Long noteId){
        noteRepository.deleteById(Math.toIntExact(noteId));
    }

    @Override
    public List<Note> getNotesForUser(String username){
        List<Note> personalNotes=noteRepository.findByOwnerUsername(username);
        return personalNotes;
    }
}

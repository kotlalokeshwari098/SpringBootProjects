package com.javaspring.server.services;

import com.javaspring.server.model.Note;

import java.util.List;

public interface NoteService {

    public Note createNoteForUser(String username, String content);

    Note updateNoteForUser(Long noteId, String content,
                           String username);

    void deleteNoteForUser(Long noteId);

    List<Note> getNotesForUser(String username);
}

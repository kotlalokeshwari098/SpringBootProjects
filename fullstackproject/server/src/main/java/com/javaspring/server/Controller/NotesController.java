package com.javaspring.server.Controller;


import com.javaspring.server.model.Note;
import com.javaspring.server.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NotesController {

    @Autowired
    private NoteService noteService;

    @PostMapping
    public Note createNote(@RequestBody String content,
                           @AuthenticationPrincipal UserDetails userDetails) {
        String username=userDetails.getUsername();
        System.out.println("USER DETAILS:"+username);
        return noteService.createNoteForUser(username,content);
    }

    @GetMapping
    public List<Note> getAllNotes(@AuthenticationPrincipal
                                      UserDetails userDetails) {
        String username=userDetails.getUsername();
        return noteService.getNotesForUser(username);
    }

    @PutMapping("/{noteId}")
    public Note updateNote(@PathVariable Long noteId,
                           @RequestBody String content,
                           @AuthenticationPrincipal
                               UserDetails userDetails
                           ){
        String username=userDetails.getUsername();
        return noteService
                .updateNoteForUser(noteId,content,username);
    }

    @DeleteMapping("/{noteId}")
    public void deleteNote(@PathVariable Long noteId){
        noteService.deleteNoteForUser(noteId);
    }

}

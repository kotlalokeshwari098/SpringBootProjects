package com.javaspring.server.repositories;

import com.javaspring.server.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Integer> {

    List<Note> findByOwnerUsername(String username);
}

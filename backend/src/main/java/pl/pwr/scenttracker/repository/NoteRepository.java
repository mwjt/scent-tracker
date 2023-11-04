package pl.pwr.scenttracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pwr.scenttracker.model.Note;

public interface NoteRepository extends JpaRepository<Note, Long> {
    Note findByName(String name);
}

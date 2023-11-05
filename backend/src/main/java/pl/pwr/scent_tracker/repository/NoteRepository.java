package pl.pwr.scent_tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pwr.scent_tracker.model.entity.Note;

public interface NoteRepository extends JpaRepository<Note, Long> {
    Note findByName(String name);
}

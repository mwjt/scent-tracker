package pl.pwr.scenttracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pwr.scenttracker.model.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag findByName(String name);
}

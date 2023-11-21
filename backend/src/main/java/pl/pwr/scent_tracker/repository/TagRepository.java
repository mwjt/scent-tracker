package pl.pwr.scent_tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pwr.scent_tracker.model.entity.Perfume;
import pl.pwr.scent_tracker.model.entity.Tag;

import java.util.List;
import java.util.Set;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag findByName(String name);
}

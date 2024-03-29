package pl.pwr.scent_tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pwr.scent_tracker.model.entity.CollectionEntry;
import pl.pwr.scent_tracker.model.entity.Perfume;
import pl.pwr.scent_tracker.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface CollectionEntryRepository extends JpaRepository<CollectionEntry, Long> {
    List<CollectionEntry> findByUser(User user);

    Optional<CollectionEntry> findByUserAndPerfume(User user, Perfume perfume);
}

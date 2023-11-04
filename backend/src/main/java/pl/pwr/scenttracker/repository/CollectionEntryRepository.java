package pl.pwr.scenttracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pwr.scenttracker.model.CollectionEntry;
import pl.pwr.scenttracker.model.User;

import java.util.List;

public interface CollectionEntryRepository extends JpaRepository<CollectionEntry, Long> {
    List<CollectionEntry> findByUser(User user);
}

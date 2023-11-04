package pl.pwr.scenttracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pwr.scenttracker.model.CollectionType;

public interface CollectionTypeRepository extends JpaRepository<CollectionType, Long> {
    CollectionType findByName(String name);
}

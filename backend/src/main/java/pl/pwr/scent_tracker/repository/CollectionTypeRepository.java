package pl.pwr.scent_tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pwr.scent_tracker.model.entity.CollectionType;

public interface CollectionTypeRepository extends JpaRepository<CollectionType, Long> {
    CollectionType findByName(String name);
}

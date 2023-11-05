package pl.pwr.scent_tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pwr.scent_tracker.model.entity.Accord;

public interface AccordRepository extends JpaRepository<Accord, Long> {
    Accord findByName(String name);
}

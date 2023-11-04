package pl.pwr.scenttracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pwr.scenttracker.model.Accord;

public interface AccordRepository extends JpaRepository<Accord, Long> {
    Accord findByName(String name);
}

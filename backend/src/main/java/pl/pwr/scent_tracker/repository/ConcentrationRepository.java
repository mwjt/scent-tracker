package pl.pwr.scent_tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pwr.scent_tracker.model.entity.Concentration;

public interface ConcentrationRepository extends JpaRepository<Concentration, Long> {
    Concentration findByName(String name);
}

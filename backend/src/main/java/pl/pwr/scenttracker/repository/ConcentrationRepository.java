package pl.pwr.scenttracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pwr.scenttracker.model.Concentration;

public interface ConcentrationRepository extends JpaRepository<Concentration, Long> {
    Concentration findByName(String name);
}

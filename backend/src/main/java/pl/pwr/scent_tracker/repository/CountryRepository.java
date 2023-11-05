package pl.pwr.scent_tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pwr.scent_tracker.model.entity.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {
    Country findByName(String name);
}

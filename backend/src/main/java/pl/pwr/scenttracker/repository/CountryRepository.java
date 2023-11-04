package pl.pwr.scenttracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pwr.scenttracker.model.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {
    Country findByName(String name);
}

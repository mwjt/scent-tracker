package pl.pwr.scenttracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pwr.scenttracker.model.Country;
import pl.pwr.scenttracker.model.Perfumer;

import java.util.List;

public interface PerfumerRepository extends JpaRepository<Perfumer, Long> {
    Perfumer findByName(String name);
    List<Perfumer> findByCountry(Country country);
}

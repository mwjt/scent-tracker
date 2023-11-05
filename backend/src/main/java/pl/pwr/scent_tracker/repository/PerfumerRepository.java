package pl.pwr.scent_tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pwr.scent_tracker.model.entity.Country;
import pl.pwr.scent_tracker.model.entity.Perfumer;

import java.util.List;

public interface PerfumerRepository extends JpaRepository<Perfumer, Long> {
    Perfumer findByName(String name);
    List<Perfumer> findByCountry(Country country);
}

package pl.pwr.scenttracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pwr.scenttracker.model.Brand;
import pl.pwr.scenttracker.model.Concentration;
import pl.pwr.scenttracker.model.Perfume;
import pl.pwr.scenttracker.model.Perfumer;

import java.util.List;

public interface PerfumeRepository extends JpaRepository<Perfume, Long> {

    Perfume findByName(String name);
    List<Perfume> findByBrand(Brand brand);
    List<Perfume> findByPerfumer(Perfumer perfumer);
    List<Perfume> findByConcentration(Concentration concentration);
    List<Perfume> findByYear(Short year);
}

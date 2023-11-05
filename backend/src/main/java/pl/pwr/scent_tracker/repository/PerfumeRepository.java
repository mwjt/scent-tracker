package pl.pwr.scent_tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pwr.scent_tracker.model.entity.Brand;
import pl.pwr.scent_tracker.model.entity.Concentration;
import pl.pwr.scent_tracker.model.entity.Perfume;
import pl.pwr.scent_tracker.model.entity.Perfumer;

import java.util.List;

public interface PerfumeRepository extends JpaRepository<Perfume, Long> {

    Perfume findByName(String name);
    List<Perfume> findByBrand(Brand brand);
    List<Perfume> findByPerfumer(Perfumer perfumer);
    List<Perfume> findByConcentration(Concentration concentration);
    List<Perfume> findByYear(Short year);
}

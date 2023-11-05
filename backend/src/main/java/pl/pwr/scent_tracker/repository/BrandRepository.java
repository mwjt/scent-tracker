package pl.pwr.scent_tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pwr.scent_tracker.model.entity.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    Brand findByName(String name);
}

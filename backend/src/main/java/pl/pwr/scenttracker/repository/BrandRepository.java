package pl.pwr.scenttracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pwr.scenttracker.model.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    Brand findByName(String name);
}

package pl.pwr.scent_tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pwr.scent_tracker.model.entity.Gallery;
import pl.pwr.scent_tracker.model.entity.Perfume;

import java.util.List;

@Repository
public interface GalleryRepository extends JpaRepository<Gallery, Long> {
}

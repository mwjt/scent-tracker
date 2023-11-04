package pl.pwr.scenttracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pwr.scenttracker.model.Perfume;
import pl.pwr.scenttracker.model.Review;
import pl.pwr.scenttracker.model.User;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByUser(User user);
    List<Perfume> findByPerfume(Perfume perfume);
}

package pl.pwr.scenttracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pwr.scenttracker.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}

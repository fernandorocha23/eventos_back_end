package pt.iscte.event.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.iscte.event.Entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Object findByName(String admin);
}

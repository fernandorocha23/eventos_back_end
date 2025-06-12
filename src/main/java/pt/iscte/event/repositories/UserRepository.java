package pt.iscte.event.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.iscte.event.Entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

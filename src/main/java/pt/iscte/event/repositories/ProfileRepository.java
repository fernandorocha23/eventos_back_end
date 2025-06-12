package pt.iscte.event.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.iscte.event.Entities.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
}

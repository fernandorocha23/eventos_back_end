package pt.iscte.event.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.iscte.event.Entities.EventArtist;

public interface EventArtistRepository extends JpaRepository<EventArtist, Long> {
}

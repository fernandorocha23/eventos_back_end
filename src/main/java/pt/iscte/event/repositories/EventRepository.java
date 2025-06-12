package pt.iscte.event.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.iscte.event.Entities.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    Event getEventById(Long id);

    Event findByName(String name);
}

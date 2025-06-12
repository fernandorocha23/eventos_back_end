package pt.iscte.event.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.iscte.event.Entities.Artist;
import pt.iscte.event.Entities.Comment;

import java.util.List;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {
    boolean existsByName(String name);

    Artist findByName(String name);
    Artist findById(long id);
}

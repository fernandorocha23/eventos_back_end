package pt.iscte.event.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.iscte.event.Entities.Comment;
import pt.iscte.event.Entities.User;
import pt.iscte.event.model.CommentDTO;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByUser(User user);
}

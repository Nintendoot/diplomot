package by.nintendo.diplomot.repository;

import by.nintendo.diplomot.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comments,Long> {
}

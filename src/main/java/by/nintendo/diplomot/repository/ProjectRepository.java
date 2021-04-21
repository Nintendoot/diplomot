package by.nintendo.diplomot.repository;

import by.nintendo.diplomot.entity.Project;
import by.nintendo.diplomot.entity.Task;
import by.nintendo.diplomot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {
    List<Project> findAllByOwner(User user);
    Optional<Project> findByIdAndUsersNotContaining(long projectId, User user);
    Optional<Project> findByIdAndUsersContaining(long projectId, User user);
    boolean existsByOwnerAndTitle(User user,String title);
}

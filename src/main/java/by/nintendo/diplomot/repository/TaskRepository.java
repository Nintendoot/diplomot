package by.nintendo.diplomot.repository;

import by.nintendo.diplomot.entity.Project;
import by.nintendo.diplomot.entity.Task;
import by.nintendo.diplomot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAllByProjectId(long id);
    Optional<Task> findByIdAndProject(long id, Project project);
    boolean existsByTitleAndProject(String title,Project project);
    List<Task>  findAllByProjectAndUsersTaskContaining(Project project, User user);
    Optional<Task> findByIdAndUsersTaskContaining(long id,User user);
    Optional<Task> findByIdAndUsersTaskNotContaining(long id,User user);
}

package by.nintendo.diplomot.repository;

import by.nintendo.diplomot.entity.Project;
import by.nintendo.diplomot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {
    public List<Project> findAllByOwner(User user);
}

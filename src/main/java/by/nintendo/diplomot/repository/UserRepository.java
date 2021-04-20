package by.nintendo.diplomot.repository;

import by.nintendo.diplomot.entity.Project;
import by.nintendo.diplomot.entity.Role;
import by.nintendo.diplomot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    public User findUserByLogin(String login);
    public List<User> findAllByRoleIsNot(Role AdminRole);
}

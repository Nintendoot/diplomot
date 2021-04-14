package by.nintendo.diplomot.service;

import by.nintendo.diplomot.entity.Project;
import by.nintendo.diplomot.entity.Role;
import by.nintendo.diplomot.entity.User;
import by.nintendo.diplomot.exception.UserWasNotFoundException;
import by.nintendo.diplomot.repository.ProjectRepository;
import by.nintendo.diplomot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void saveUser(User user) {
        if (userRepository.findUserByLogin(user.getLogin()) != null) {
            throw new UserWasNotFoundException("Usfdsfdfsdfsdf");
        } else {
            user.setRole(Role.USER);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        }
    }

    public List<User> findUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new UserWasNotFoundException("deleate");
        }
    }

    public Optional<User> findUserById(long id) {
        return userRepository.findById(id);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public User fundByLogin(String login) {
        boolean b = userRepository.findAll().stream()
                .anyMatch(x -> x.getLogin().equals(login));
        if (b) {
            return userRepository.findUserByLogin(login);
        } else {
            throw new UserWasNotFoundException("No found.");
        }
    }

    public void addUserInProject(long id, String login){
        User userByLogin = userRepository.findUserByLogin(login);
       projectRepository.getOne(id).getUsers().add(userByLogin);
       // projectRepository.save()
    }

}

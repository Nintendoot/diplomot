package by.nintendo.diplomot.service;

import by.nintendo.diplomot.entity.Project;
import by.nintendo.diplomot.entity.ProjectStatus;
import by.nintendo.diplomot.entity.Role;
import by.nintendo.diplomot.entity.User;
import by.nintendo.diplomot.exception.ProjectNotFountException;
import by.nintendo.diplomot.exception.UserWasNotFoundException;
import by.nintendo.diplomot.repository.ProjectRepository;
import by.nintendo.diplomot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ProjectService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private DateService dateService;

    public void createProject(Project project) {
        User creatorProject = sessionService.getSession();
        if (creatorProject.getRole().equals(Role.USER)) {
            project.setCreatTime(dateService.Time());
            project.setProjectStatus(ProjectStatus.NOT_STARTED);
            project.setOwner(creatorProject);
            projectRepository.save(project);
        } else {
            throw new UserWasNotFoundException("Smena Exceptiona");
        }
    }

    public List<Project> allProjectsByManager() {
        return projectRepository.findAllByOwner(sessionService.getSession());
    }

    public Optional<Project> projectById(long id) {
        if (projectRepository.existsById(id)) {
            return projectRepository.findById(id);
        } else {
            throw new ProjectNotFountException("fvdfvdfvdfv");
        }
    }

    public void deleteProject(long id) {
        if (projectRepository.existsById(id)) {
            projectRepository.deleteById(id);
        } else {
            throw new ProjectNotFountException("fvdfvdfvdfv");
        }
    }

    public void updateProject(Project project) {
        if (projectRepository.existsById(project.getId())) {
            project.setOwner(sessionService.getSession());
            projectRepository.save(project);
        } else {
            throw new ProjectNotFountException("fvdfvdfvdfv");
        }
    }

    public void addUserForProject(long id, String login,User auth) {
//       userService.addUserInProject(id,login);
        User userByLogin = userRepository.findUserByLogin(login);
        Optional<Project> byId = projectRepository.findById(id);
        byId.get().getUsers().add(userByLogin);

    }

//    User userByLogin = userRepository.findUserByLogin(login);
//        userByLogin.getProjects().add(projectRepository.getOne(id));
//        userRepository.save(userByLogin);

}

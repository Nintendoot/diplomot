package by.nintendo.diplomot.service;

import by.nintendo.diplomot.entity.*;
import by.nintendo.diplomot.exception.ActionNotPossibleException;
import by.nintendo.diplomot.exception.ProjectNotFountException;
import by.nintendo.diplomot.exception.TitleAlreadyExistsException;
import by.nintendo.diplomot.exception.UserWasNotFoundException;
import by.nintendo.diplomot.repository.ProjectRepository;
import by.nintendo.diplomot.repository.TaskRepository;
import by.nintendo.diplomot.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
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
    @Autowired
    private TaskRepository taskRepository;

    public void createProject(Project project) {
        log.info("Call method: createProject(project: " + project + ") ");
        User creatorProject = sessionService.getSession();
        if (creatorProject.getRole().equals(Role.USER)) {
            if (!projectRepository.existsByOwnerAndTitle(project.getOwner(), project.getTitle())) {
                project.setCreatTime(dateService.Time());
                project.setProjectStatus(ProjectStatus.NOT_STARTED);
                project.setOwner(creatorProject);
                projectRepository.save(project);
                log.info("project: " + project + " save. ");
            } else {
                throw new TitleAlreadyExistsException("There is already a project name for this owner.");
            }
        } else {
            throw new ActionNotPossibleException("Action not possible.");
        }

    }

    public List<Project> allProjectsByManager(boolean i) {
        log.info("Call method: allProjectsByManager()");
        if(i){
            List<Project>  projects=projectRepository.findAllByOwner(sessionService.getSession());
            return projects.stream().
                    filter(x -> !x.getProjectStatus().equals(ProjectStatus.COMPLETED)).
                    collect(Collectors.toList());
        }else{
            List<Project>  projects=projectRepository.findAllByOwner(sessionService.getSession());
            return projects.stream().
                    filter(x -> x.getProjectStatus().equals(ProjectStatus.COMPLETED)).
                    collect(Collectors.toList());
        }
    }

    public Optional<Project> projectById(long id) {
        log.info("Call method: projectById(long: " + id + ") ");
        if (projectRepository.existsById(id)) {
            log.info("Find project byId: " + id);
            Optional<Project> byId = projectRepository.findById(id);
            return byId;
        } else {
            throw new ProjectNotFountException("Project not found.");
        }
    }

    public void deleteProject(long id) {
        log.info("Call method:deleteProject(long: " + id + ") ");
        Optional<Project> project = projectRepository.findById(id);
        if (project.isPresent()) {
            projectRepository.delete(project.get());
            log.info("Delete project byID " + id);
        } else {
            throw new ProjectNotFountException("Project not found.");
        }
    }

    public void updateProject(Project project, User user, long id) {
        log.info("Call method:updateProject(Project: " + project + ") ");
        Optional<Project> projectById = projectRepository.findByIdAndOwner(id, user);
        if (projectById.isPresent()) {
            projectById.get().setTitle(project.getTitle());
            projectById.get().setProjectStatus(project.getProjectStatus());
            projectById.get().setShortName(project.getShortName());
            projectById.get().setDescription(project.getDescription());
            if(project.getProjectStatus().equals(ProjectStatus.COMPLETED)){
                projectById.get().setEndTime(dateService.Time());
            }
            projectRepository.save(projectById.get());
            log.info("Update project: " + project);
        } else {
            throw new ProjectNotFountException("Project not found.");
        }
    }

    public void addUserForProject(long id, String login) {
        log.info("Call method:addUserForProject(long: " + id + "String " + login + ") ");
        User userByLogin = userRepository.findUserByLogin(login);
        if (userByLogin != null) {
            Optional<Project> project = projectRepository.findByIdAndUsersNotContaining(id, userByLogin);
            if (project.isPresent() && !userByLogin.getLogin().equals(project.get().getOwner().getLogin())) {
                project.get().getUsers().add(userByLogin);
                projectRepository.save(project.get());
                log.info("User: " + login + "add in project: " + project.get().getId());
            } else {
                throw new ActionNotPossibleException("Action not possible!!!!Such a user already exists or he is the owner");
            }
        } else {
            throw new UserWasNotFoundException("User not found.");
        }
    }

    public List<Project> allProjects() {
        log.info("Call method:allProjects()");
        return projectRepository.findAll();
    }

    public void deleteUserByProject(long id, String login) {
        log.info("Call method:deleteUserByProject(long: " + id + "String " + login + ") ");
        User user = userRepository.findUserByLogin(login);
        if (user != null) {
            Optional<Project> project = projectRepository.findByIdAndUsersContaining(id, user);
            if (project.isPresent() && !user.equals(project.get().getOwner())) {
                project.get().getUsers().removeIf(x -> x.getLogin().equals(user.getLogin()));
                projectRepository.save(project.get());
                log.info("User: " + login + "delete in project: " + project.get().getId());
            } else {
                throw new ActionNotPossibleException("Action not possible!!!!User is the owner");
            }
        } else {
            throw new UserWasNotFoundException("User not found.");
        }

    }

    public Optional<Project> findProjectById(long id) {
        log.info("Call method:findProjectById(long: " + id + ") ");
        return projectRepository.findById(id);
    }

}

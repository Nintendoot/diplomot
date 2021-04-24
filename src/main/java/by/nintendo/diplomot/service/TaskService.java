package by.nintendo.diplomot.service;

import by.nintendo.diplomot.entity.Project;
import by.nintendo.diplomot.entity.Task;
import by.nintendo.diplomot.entity.TaskStatus;
import by.nintendo.diplomot.entity.User;
import by.nintendo.diplomot.exception.*;
import by.nintendo.diplomot.repository.ProjectRepository;
import by.nintendo.diplomot.repository.TaskRepository;
import by.nintendo.diplomot.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private DateService dateService;
    @Autowired
    private UserRepository userRepository;

    public void createTask(Task task, long id) {
        log.info("Call method: createTask(project id: " + id + ",task " + task + ") ");
        Optional<Project> project = projectRepository.findById(id);
        if (project.isPresent()) {
            if (!taskRepository.existsByTitleAndProject(task.getTitle(), project.get())) {
                task.setDateStart(dateService.Time());
                task.setTaskStatus(TaskStatus.NOT_STARTED);
                task.setProject(project.get());
                taskRepository.save(task);
                log.info("Task create: " + task);
            } else {
                throw new TitleAlreadyExistsException("Title already exists in project.");
            }
        } else {
            throw new ProjectNotFountException("Project not found");
        }
    }

    public void deleteTask(long projectId, long taskId) {
        log.info("Call method: deleteTask(project id: " + projectId + ",task id " + taskId + ") ");
        Optional<Project> project = projectRepository.findById(projectId);
        if (project.isPresent()) {
            Optional<Task> task = taskRepository.findByIdAndProject(taskId, project.get());
            if (task.isPresent()) {
                taskRepository.delete(task.get());
                log.info("Task: " + task.get() + " delete.");
            } else {
                throw new TaskNotFoundException("Task not found.");
            }
        } else {
            throw new ProjectNotFountException("Project not found");
        }
    }

    public Optional<Task> getTaskById(long taskId) {
        Optional<Task> task = taskRepository.findById(taskId);
        if (task.isPresent()) {
            return task;
        } else {
            throw new TaskNotFoundException("Task not found.");
        }
    }

    public void updateTask(Task task, long id, long idProject) {
        log.info("Call method:updateTask(Task: " + task + ") ");
        Optional<Project> project = projectRepository.findById(idProject);
        if (project.isPresent()) {
            Optional<Task> ta = taskRepository.findByIdAndProject(id,project.get());
            if (ta.isPresent()) {
                ta.get().setTitle(task.getTitle());
                ta.get().setDescription(task.getDescription());
                ta.get().setPriority(task.getPriority());
                ta.get().setTaskStatus(task.getTaskStatus());
                taskRepository.save(ta.get());
                log.info("Update task id: " + id);
            } else {
                throw new ProjectNotFountException("Project not found.");
            }
        } else {
            throw new ProjectNotFountException("Project not found.");
        }
    }

    public void deleteUserByTask(long idUser, long idTask, long idProject) {
        log.info("Call method:deleteUserByTask(ser id: " + idUser + " ,task id " + idTask + " ,user id " + idProject + ") ");

          Optional<User> user = userRepository.findById(idUser);
          if(user.isPresent()){
              Optional<Task> task = taskRepository.findByIdAndUsersTaskContaining(idTask, user.get());
              if (task.isPresent()) {
                  task.get().getUsersTask().removeIf(x->x.getLogin().equals(user.get().getLogin()));
                  taskRepository.save(task.get());
                  log.info("Delete user()id " + idUser + " by task(id) " + idTask);
              } else {
                  throw new TaskNotFoundException("Task not found.");
              }
          }else{
              throw new UserWasNotFoundException("User not found");
          }

    }

    public void addUserByTask(String login, long idTask, long idProject) {
        log.info("Call method:addUserByTask(User login: " + login + " ,task id " + idTask + " ,user id " + idProject + ") ");
        Optional<Project> project = projectRepository.findById(idProject);
        if (project.isPresent()) {
           User user= userRepository.findUserByLogin(login);
            Optional<Task> task = taskRepository.findByIdAndUsersTaskNotContaining(idTask,user);
            if (task.isPresent()) {
                task.get().getUsersTask().add(user);
                taskRepository.save(task.get());
                log.info("Add user(login) " + login + " by task(id) " + idTask);
            } else {
                throw new UserAlreadyExistsException("User already exist in task id "+idTask);
            }
        } else {
            throw new ProjectNotFountException("Project not found.");
        }
    }
}

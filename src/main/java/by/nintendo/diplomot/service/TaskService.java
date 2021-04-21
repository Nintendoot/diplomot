package by.nintendo.diplomot.service;

import by.nintendo.diplomot.entity.Project;
import by.nintendo.diplomot.entity.Task;
import by.nintendo.diplomot.entity.TaskStatus;
import by.nintendo.diplomot.exception.ProjectNotFountException;
import by.nintendo.diplomot.exception.TaskNotFoundException;
import by.nintendo.diplomot.exception.TitleAlreadyExistsException;
import by.nintendo.diplomot.repository.ProjectRepository;
import by.nintendo.diplomot.repository.TaskRepository;
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

    public void createTask(Task task, long id) {
        log.info("Call method: createTask(project id: " + id + ",task " + task + ") ");
        Optional<Project> project = projectRepository.findById(id);
        if (project.isPresent()) {
            if(!taskRepository.existsByTitleAndProject(task.getTitle(),project.get())){
                task.setDateStart(dateService.Time());
                task.setStatus(TaskStatus.NOT_STARTED);
                task.setProject(project.get());
                taskRepository.save(task);
                log.info("Task create: " + task);
            }else{
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

    public Optional<Task> getTaskByIdAndProjectId(long taskId, long projectId){
        Optional<Project> project = projectRepository.findById(projectId);
        if(project.isPresent()){
          return   taskRepository.findTasksByIdAndProject(taskId,project.get());
        }else{
            throw new ProjectNotFountException("Project not found.");
        }
    }

    public void updateTask(Task task) {
        log.info("Call method:updateTask(Task: " + task + ") ");
        Optional<Task> ta = taskRepository.findById(task.getId());
        if (ta.isPresent()) {
            taskRepository.save(task);
            log.info("Update task: " + ta.get());
        } else {
            throw new ProjectNotFountException("Project not found.");
        }
    }
}

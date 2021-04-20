package by.nintendo.diplomot.service;

import by.nintendo.diplomot.entity.Project;
import by.nintendo.diplomot.entity.Task;
import by.nintendo.diplomot.entity.TaskStatus;
import by.nintendo.diplomot.exception.ProjectNotFountException;
import by.nintendo.diplomot.repository.ProjectRepository;
import by.nintendo.diplomot.repository.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public void createTask(Task task,long id){
        log.info("Call method: createTask(project id: " + id + ",task "+task+ ") ");
        Optional<Project> project = projectRepository.findById(id);
        if(project.isPresent()){
            task.setDateStart(dateService.Time());
            task.setStatus(TaskStatus.NOT_STARTED);
            project.get().getTasks().add(task);
            projectRepository.save(project.get());
            log.info("Task create: "+task);
        }else{
            throw new ProjectNotFountException("Project not found");
        }
    }
}

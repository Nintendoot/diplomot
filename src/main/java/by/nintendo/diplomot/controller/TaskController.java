package by.nintendo.diplomot.controller;

import by.nintendo.diplomot.entity.*;
import by.nintendo.diplomot.service.ProjectService;
import by.nintendo.diplomot.service.SessionService;
import by.nintendo.diplomot.service.TaskService;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping(path = "/project/{idProject}")
public class TaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private SessionService sessionService;

    @GetMapping(path = "/task")
    public ModelAndView createTask(@PathVariable("idProject") long idProject, ModelAndView modelAndView) {
        log.info("GET request /project/"+idProject+"/task");
        modelAndView.setViewName("task/taskView");
        modelAndView.addObject("idP", idProject);
        modelAndView.addObject("priority", Priority.values());
        modelAndView.addObject("newTask", new Task());
        return modelAndView;
    }

    @PostMapping("/newTask")
    public ModelAndView createTask(@Valid @ModelAttribute("newTask") Task task,
                                   BindingResult result,@PathVariable("idProject") long idProject,
                                   ModelAndView modelAndView) {
        log.info("POST request /project/" + idProject + "/newTask");
        if (result.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError fieldError : result.getFieldErrors()) {
                errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            log.info("POST request /project/" + idProject + "/newTask has error.");
            modelAndView.setViewName("task/taskView");
        } else {
            taskService.createTask(task, idProject);
            modelAndView.setViewName("redirect:/project/" + idProject + "/allTask");
        }
        return modelAndView;
    }
    @PostMapping("/deleteTask/{id}")
    public ModelAndView createTask(@PathVariable("idProject") long idProject,
                                   @PathVariable("id") long id,
                                   ModelAndView modelAndView) {
        log.info("POST request /project/" + idProject + "/deleteTask/"+id);
            taskService.deleteTask(idProject,id);
            modelAndView.setViewName("redirect:/project/" + idProject + "/allTask");
        return modelAndView;
    }
    @GetMapping(path = "task/{id}")
    public ModelAndView taskView(@PathVariable("id") long id,
                                 @PathVariable("idProject") long idProject, ModelAndView modelAndView) {
        log.info("GET request /project/"+idProject+"/task/"+ id);
        Optional<Task> task = taskService.getTaskById(id);
        Optional<Project> project = projectService.projectById(idProject);
        modelAndView.addObject("updateTask", new Task());
//        modelAndView.addObject("taskUsers", task.get().getUsersTask());
        modelAndView.addObject("task", task.get());
        modelAndView.addObject("idPr",idProject);
        modelAndView.addObject("projectUsers",project.get().getUsers());
        modelAndView.addObject("idT",id);
        modelAndView.addObject("taskStatus", ProjectStatus.values());
        modelAndView.addObject("priority", Priority.values());
        modelAndView.setViewName("task/task-page");
        return modelAndView;
    }

    @PostMapping(path = "/task/{id}")
    public ModelAndView editTask(@ModelAttribute("updateTask") Task task,
                                 @PathVariable long idProject,
                                 @PathVariable long id,
                                       ModelAndView modelAndView) {
        log.info("POST request /project/"+idProject+"/editTask/"+ task.getId());
        taskService.updateTask(task,id,idProject);
        modelAndView.setViewName("redirect:/project/"+idProject+"/allTask");
        return modelAndView;
    }

    @PostMapping(path = "/task/{idTask}/deleteUser/{idUser}")
    public ModelAndView deleteUserByTask(@PathVariable("idProject") long idProject,
                                 @PathVariable("idTask") long idTask,
                                 @PathVariable("idUser") long id,
                                 ModelAndView modelAndView) {
        log.info("POST request /project/"+idProject+"/task/"+ idTask+"/deleteUser/"+id);
        taskService.deleteUserByTask(id,idTask,idProject);
        modelAndView.setViewName("redirect:/project/"+idProject+"/task/"+idTask);
        return modelAndView;
    }

    @PostMapping(path = "/task/{idTask}/addUser")
    public ModelAndView addUserByTask(@PathVariable("idProject") long idProject,
                                         @PathVariable("idTask") long idTask,
                                         String login,
                                         ModelAndView modelAndView) {
        log.info("POST request /project/"+idProject+"/task/"+ idTask+"/addUser");
        taskService.addUserByTask(login,idTask,idProject);
        modelAndView.setViewName("redirect:/project/"+idProject+"/task/"+idTask);
        return modelAndView;
    }
}

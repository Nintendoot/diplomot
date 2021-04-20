package by.nintendo.diplomot.controller;

import by.nintendo.diplomot.entity.Project;
import by.nintendo.diplomot.entity.ProjectStatus;
import by.nintendo.diplomot.entity.Task;
import by.nintendo.diplomot.entity.User;
import by.nintendo.diplomot.repository.TaskRepository;
import by.nintendo.diplomot.service.ProjectService;
import by.nintendo.diplomot.service.SessionService;
import by.nintendo.diplomot.service.TaskService;
import by.nintendo.diplomot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
@Slf4j
@Controller
@RequestMapping(path = "/project")
public class ProjectController {
    @Autowired
    private UserService userService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private SessionService sessionService;

    @GetMapping
    public ModelAndView createProjectView(ModelAndView modelAndView) {
        log.info("GET request /project");
        modelAndView.setViewName("project/projectView");
        modelAndView.addObject("newProject", new Project());
        return modelAndView;
    }

    @PostMapping
    public ModelAndView createProject(@ModelAttribute("newProject") Project project, ModelAndView modelAndView) {
        log.info("POST request /project");
        projectService.createProject(project);
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping(path = "/all")
    public ModelAndView allProject(ModelAndView modelAndView) {
        log.info("GET request /project/all");
        modelAndView.addObject("allProjects", projectService.allProjectsByManager());
        modelAndView.addObject("all",projectService.allProjects());
        modelAndView.setViewName("project/allProject");
        return modelAndView;
    }

    @GetMapping(path = "/view/{id}")
    public ModelAndView projectView(@PathVariable("id") long id, ModelAndView modelAndView) {
        log.info("GET request /project/view/"+id);
        Optional<Project> project = projectService.projectById(id);
        modelAndView.addObject("updateProject", new Project());
        modelAndView.addObject("project", project);
        modelAndView.addObject("projectStatus", ProjectStatus.values());
        modelAndView.setViewName("project/project-page");
        return modelAndView;
    }

    @PostMapping(path = "/delete/{id}")
    public ModelAndView deleateProject(@PathVariable("id") long id, ModelAndView modelAndView) {
        log.info("POST request /project/delete/"+id);
        projectService.deleteProject(id);
        modelAndView.setViewName("redirect:/project/all");
        return modelAndView;
    }

    @PostMapping(path = "/update")
    public ModelAndView updateProject(@ModelAttribute("updateProject") Project project,ModelAndView modelAndView) {
        log.info("POST request /project/update");
        long id = project.getId();
        modelAndView.setViewName("redirect:/project/all");
        projectService.updateProject(project);
        return modelAndView;
    }

    @PostMapping(path = "/addUser/{id}")
    public ModelAndView addUserInProject(@PathVariable("id") long id, String login, ModelAndView modelAndView) {
        log.info("POST request /project/addUser/"+id);
        projectService.addUserForProject(id, login);
        modelAndView.setViewName("redirect:/project/view/"+id);
        return modelAndView;
    }

    @PostMapping(path = "/deleteUser/{id}")
    public ModelAndView deleteUserInProject(@PathVariable("id") long id, String login, ModelAndView modelAndView) {
        log.info("POST request /project/deleteUser/"+id);
        projectService.deleteUserByProject(id,login);
        modelAndView.setViewName("redirect:/project/view/"+id);
        return modelAndView;
    }

    @GetMapping(path = "{id}/allTask")
    public ModelAndView allTasks(@PathVariable("id") long id,ModelAndView modelAndView){
        log.info("GET request /project/task/"+id);
        modelAndView.addObject("idProject",id);
        modelAndView.addObject("allTask",projectService.findProjectById(id).get().getTasks());
        modelAndView.setViewName("task/allTask");
        return modelAndView;
    }

//    @PostMapping(path = "/{id}/task")
//    public ModelAndView createTaskByProject(@Valid @ModelAttribute("newTask") Task task, BindingResult result,@PathVariable("id") long id, ModelAndView modelAndView) {
//        log.info("POST request /task");
//        if (result.hasErrors()) {
//            Map<String, String> errorMap = new HashMap<>();
//            for (FieldError fieldError : result.getFieldErrors()) {
//                errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
//            }
//            modelAndView.setViewName("task/taskView");
//        }
//        taskService.createTask(task,id);
//        modelAndView.setViewName("index");
//        return modelAndView;
//    }
}

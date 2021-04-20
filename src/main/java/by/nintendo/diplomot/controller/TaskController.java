package by.nintendo.diplomot.controller;

import by.nintendo.diplomot.entity.Priority;
import by.nintendo.diplomot.entity.Project;
import by.nintendo.diplomot.entity.Task;
import by.nintendo.diplomot.service.TaskService;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
@Slf4j
@Controller
@RequestMapping(path = "/project/{idProject}")
public class TaskController {
    @Autowired
   private TaskService taskService;

    @GetMapping(path = "/task")
    public ModelAndView createTask(@PathVariable("idProject") long idProject, ModelAndView modelAndView) {
        log.info("GET request /task");
        modelAndView.setViewName("task/taskView");
        modelAndView.addObject("idP",idProject);
        modelAndView.addObject("priority", Priority.values());
        modelAndView.addObject("newTask", new Task());
        return modelAndView;
    }

    @PostMapping("/newTask")
    public ModelAndView createTask(@PathVariable("idProject") long idProject, @ModelAttribute("newTask") Task task, ModelAndView modelAndView) {
        log.info("POST request /task");
        taskService.createTask(task,idProject);
        modelAndView.setViewName("index");
        return modelAndView;
    }
}

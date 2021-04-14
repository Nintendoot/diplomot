package by.nintendo.diplomot.controller;

import by.nintendo.diplomot.entity.Project;
import by.nintendo.diplomot.entity.ProjectStatus;
import by.nintendo.diplomot.entity.User;
import by.nintendo.diplomot.service.ProjectService;
import by.nintendo.diplomot.service.SessionService;
import by.nintendo.diplomot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping(path = "/")
public class ProjectController {
    @Autowired
    private UserService userService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private SessionService sessionService;

    @GetMapping(path = "/createProject")
    public ModelAndView createProjectView(ModelAndView modelAndView) {
        modelAndView.setViewName("project/projectView");
        modelAndView.addObject("newProject",new Project());
        return modelAndView;
    }
    @PostMapping(path = "/createProject")
    public ModelAndView createProject(@ModelAttribute("newProject") Project project, ModelAndView modelAndView) {
        projectService.createProject(project);
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping(path = "/allProject")
    public ModelAndView allProject(ModelAndView modelAndView) {
        modelAndView.addObject("allProjects",projectService.allProjectsByManager());
        modelAndView.setViewName("project/allProject");
        return modelAndView;
    }
    @GetMapping(path = "/project")
    public ModelAndView projectView( long id, ModelAndView modelAndView) {
     Optional<Project>  project=projectService.projectById(id);
        modelAndView.addObject("updateProject",new Project());
        modelAndView.addObject("project",project);
//        modelAndView.addObject("allUsers",project.getUsers());
        modelAndView.addObject("projectStatus", ProjectStatus.values());
        modelAndView.setViewName("project/project-page");
        return modelAndView;
    }

    @PostMapping(path = "/deleteProject")
    public ModelAndView deleateProject(Long id, ModelAndView modelAndView) {
        projectService.deleteProject(id);
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @PostMapping(path = "/updateProject")
    public ModelAndView updateProject(@ModelAttribute("updateProject") Project project, ModelAndView modelAndView) {
        projectService.updateProject(project);
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @PostMapping(path = "/addUser")
    public ModelAndView addUserInProject(long id,String login,ModelAndView modelAndView){
        projectService.addUserForProject(id,login,sessionService.getSession());
        modelAndView.setViewName("index");
        return modelAndView;
    }
}

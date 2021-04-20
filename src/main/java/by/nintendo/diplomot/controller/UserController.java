package by.nintendo.diplomot.controller;

import by.nintendo.diplomot.entity.Role;
import by.nintendo.diplomot.entity.User;
import by.nintendo.diplomot.service.SessionService;
import by.nintendo.diplomot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
@Slf4j
@Controller
@RequestMapping(path = "/")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private SessionService sessionService;

    @GetMapping("/findAll")
    public ModelAndView findAll(ModelAndView modelAndView) {
        log.info("GET request /findAll");
            modelAndView.addObject("allUsers", userService.findUsers());
            modelAndView.setViewName("user/findAllUsers");
        return modelAndView;
    }

    @GetMapping(path = "/user/{id}")
    public ModelAndView UserView(@PathVariable("id") Long id, ModelAndView modelAndView) {
        log.info("GET request /user/"+id);
        Optional<User> us = userService.findUserById(id);
        modelAndView.addObject("user", us);
        modelAndView.addObject("userRole", Role.values());
        modelAndView.setViewName("user/user-page");
        return modelAndView;
    }

    @PostMapping(path = "/edit")
    public ModelAndView editUser(@ModelAttribute("user") User user, BindingResult result, ModelAndView modelAndView) {
        log.info("POST request /edit");
        userService.updateUser(user);
//        modelAndView.addObject("updateUser", "User update.");
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @PostMapping(path = "/delete/{id}")
    public ModelAndView deleateUser(@PathVariable("id") Long id, ModelAndView modelAndView) {
        log.info("POST request /delete/"+id);
        userService.deleteUser(id);
        if(sessionService.getSession().getId()==id){
            modelAndView.setViewName("redirect:/logout");
            return modelAndView;
        }
        modelAndView.setViewName("redirect:/findAll");
        return modelAndView;
    }

    @GetMapping(path = "/find")
    public ModelAndView findtUserView(ModelAndView modelAndView) {
        log.info("GET request /find");
        modelAndView.setViewName("user/findUserView");
        return modelAndView;
    }

    @PostMapping(path = "/find")
    public ModelAndView findtUser(String login, ModelAndView modelAndView) {
        log.info("POST request /find");
        modelAndView.addObject("userFound", userService.fundByLogin(login));
        modelAndView.setViewName("user/findUser");
        return modelAndView;
    }
}

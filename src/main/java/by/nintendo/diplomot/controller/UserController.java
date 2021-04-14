package by.nintendo.diplomot.controller;

import by.nintendo.diplomot.entity.Role;
import by.nintendo.diplomot.entity.User;
import by.nintendo.diplomot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/findAll")
    public ModelAndView findAll(ModelAndView modelAndView) {
        modelAndView.addObject("allUsers", userService.findUsers());
        modelAndView.setViewName("user/findAllUsers");
        return modelAndView;
    }

    @GetMapping(path = "/user")
    public ModelAndView UserView(long id, ModelAndView modelAndView) {
        Optional<User> us = userService.findUserById(id);
        modelAndView.addObject("user", us);
        modelAndView.addObject("userRole", Role.values());
        modelAndView.setViewName("user/user-page");
        return modelAndView;
    }

    @PostMapping(path = "/edit")
    public ModelAndView editUser(@ModelAttribute("user") User user, ModelAndView modelAndView) {
        userService.updateUser(user);
        modelAndView.addObject("updateUser", "User update.");
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @PostMapping(path = "/delete")
    public ModelAndView deleateUser(Long id, ModelAndView modelAndView) {
        userService.deleteUser(id);
        modelAndView.addObject("userDelete", "User delete.");
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping(path = "/find")
    public ModelAndView findtUserView(ModelAndView modelAndView) {
        modelAndView.setViewName("user/findUserView");
        return modelAndView;
    }

    @PostMapping(path = "/find")
    public ModelAndView findtUser(String login, ModelAndView modelAndView) {
        modelAndView.addObject("userFound", userService.fundByLogin(login));
        modelAndView.setViewName("user/findUser");
        return modelAndView;
    }


}

package by.nintendo.diplomot.controller;

import by.nintendo.diplomot.entity.Role;
import by.nintendo.diplomot.entity.User;
import by.nintendo.diplomot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping(path = "/")
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping(path = "/edit")
    public ModelAndView editUserView(long id, ModelAndView modelAndView){
        modelAndView.addObject("userEdit",userService.findUserById(id));
        modelAndView.addObject("userRole", Role.values());
        modelAndView.setViewName("user-page");
        return modelAndView;
    }

    @PostMapping(path = "/edit")
    public ModelAndView editUser(@ModelAttribute("userEdit") User user, ModelAndView modelAndView){
        userService.updateUser(user);
        modelAndView.addObject("updateUser","User update.");
        modelAndView.setViewName("findAllUsers");
        return modelAndView;
    }

    @PostMapping(path = "/delete")
    public ModelAndView deleateUser(Long id, ModelAndView modelAndView){
        userService.deleteUser(id);
        modelAndView.addObject("userDelete","User delete.");
        modelAndView.setViewName("findAllUsers");
        return modelAndView;
    }

    @GetMapping(path = "/find")
    public ModelAndView findtUserView( ModelAndView modelAndView){
        modelAndView.setViewName("findUserView");
        return modelAndView;
    }
    @PostMapping(path = "/find")
    public ModelAndView findtUser(String login, ModelAndView modelAndView){
        modelAndView.addObject("findUser",userService.fundByLogin(login));
        modelAndView.setViewName("findUser");
        return modelAndView;
    }

}

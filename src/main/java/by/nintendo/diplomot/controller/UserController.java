package by.nintendo.diplomot.controller;

import by.nintendo.diplomot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/findAll")
    public ModelAndView findAll(ModelAndView modelAndView) {
        modelAndView.addObject("allUsers",userService.findUsers());
        modelAndView.setViewName("findAllUsers");
        return modelAndView;
    }

}

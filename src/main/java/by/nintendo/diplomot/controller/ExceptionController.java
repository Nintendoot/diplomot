package by.nintendo.diplomot.controller;

import by.nintendo.diplomot.exception.ProjectNotFountException;
import by.nintendo.diplomot.exception.UserWasNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@Slf4j
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(UserWasNotFoundException.class)
    public String userNotFound(UserWasNotFoundException e, Model model) {
       // log.error("error",e.fillInStackTrace());
        model.addAttribute("errorMessage",e);
        return "errorPage";
    }

    @ExceptionHandler(ProjectNotFountException.class)
    public String projectNotFound(ProjectNotFountException e, Model model) {
        // log.error("error",e.fillInStackTrace());
        model.addAttribute("errorMessage",e);
        return "errorPage";
    }
}

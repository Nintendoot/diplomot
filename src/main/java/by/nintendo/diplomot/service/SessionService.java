package by.nintendo.diplomot.service;

import by.nintendo.diplomot.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class SessionService {

    @Autowired
    private UserService userService;

    public User getSession(){
        log.info("Call method: getSession()");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String login = ((UserDetails)principal).getUsername();
        log.info("Session:"+principal);
        return userService.fundByLogin(login);
    }
}

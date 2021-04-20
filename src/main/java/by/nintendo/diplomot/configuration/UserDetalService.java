package by.nintendo.diplomot.configuration;

import by.nintendo.diplomot.exception.UserWasNotFoundException;
import by.nintendo.diplomot.entity.User;
import by.nintendo.diplomot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetalService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User us=userRepository.findUserByLogin(login);
        if (us == null) {
            throw new UserWasNotFoundException("User not found");
        } else {
            UserDetails uss= org.springframework.security.core.userdetails.User.builder()
                    .username(us.getLogin())
                    .password(us.getPassword())
                    .roles(us.getRole().getIteam())
                    .build();
            return uss;
        }


    }
}

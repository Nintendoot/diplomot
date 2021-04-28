package by.nintendo.diplomot.configuration;

import by.nintendo.diplomot.exception.AuthenticationExeption;
import by.nintendo.diplomot.entity.User;
import by.nintendo.diplomot.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserDetalService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetalService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login)  {
        User us=userRepository.findUserByLogin(login);
        if (us == null) {
            throw new AuthenticationExeption("User not found or wrong password.");
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

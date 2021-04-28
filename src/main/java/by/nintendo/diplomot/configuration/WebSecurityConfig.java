package by.nintendo.diplomot.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetalService userDetalService;

    public WebSecurityConfig(UserDetalService userDetalService) {
        this.userDetalService = userDetalService;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable().and().csrf().ignoringAntMatchers("/h2-console/**").and().cors().disable();
        http.
                authorizeRequests()
                .antMatchers("/", "/home", "/reg", "/h2-console/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .usernameParameter("login")
                .loginPage("/auth").
                defaultSuccessUrl("/home")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/myLogout")
                .clearAuthentication(true)
                .permitAll()
                .logoutSuccessUrl("/");
    }

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetalService).passwordEncoder(bCryptPasswordEncoder());
    }

}

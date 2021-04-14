package by.nintendo.diplomot.configuration;

import by.nintendo.diplomot.service.UserDetalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetalService userDetalService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
//                .antMatchers("/", "/reg","/h2-console/**").permitAll().and().headers().frameOptions().disable().and().csrf().ignoringAntMatchers("/h2-console/**").and().cors().disable();

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable().and().csrf().ignoringAntMatchers("/h2-console/**").and().cors().disable();
        http.authorizeRequests()
                .antMatchers("/","/home", "/reg", "/h2-console/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .usernameParameter("login")
                .loginPage("/auth").
                defaultSuccessUrl("/home")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/myLogout").logoutSuccessUrl("/")
                .permitAll().logoutSuccessUrl("/");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetalService).passwordEncoder(bCryptPasswordEncoder());
    }

}

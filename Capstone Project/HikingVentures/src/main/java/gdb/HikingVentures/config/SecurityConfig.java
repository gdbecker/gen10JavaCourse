package gdb.HikingVentures.config;

import gdb.HikingVentures.service.HVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Capstone Project
 * @author garrettbecker
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    //Wire in the HVService (which includes all of the security methods)
    @Autowired
    HVService service;
    
    /*
    @Autowired
    public void configureGlobalInMemory(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("user").password("{noop}password").roles("USER")
                .and()
                .withUser("admin").password("{noop}password").roles("ADMIN", "USER");
    }
    */
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http    
                .authorizeRequests()
                    .antMatchers("/login").hasRole("ADMIN")
                    .antMatchers("/**").permitAll()
                    .antMatchers("/css/**", "/js/**", "/fonts/**").permitAll()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .failureUrl("/login?login_error=1")
                    .permitAll();
                /*.and()
                .logout()
                    .logoutSuccessUrl("/")
                    .permitAll();   */       
    }
    
    @Autowired
    public void configureGlobalInDB(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service).passwordEncoder(bCryptPasswordEncoder());
    }
    
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

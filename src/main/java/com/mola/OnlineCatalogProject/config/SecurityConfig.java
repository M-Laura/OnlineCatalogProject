package com.mola.OnlineCatalogProject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DatabaseUserDetailsService databaseUserDetailsService;


//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder authenticationMgr) throws Exception {
//        authenticationMgr.inMemoryAuthentication()
//                .withUser("user").password("user").authorities("ROLE_USER")
//                .and()
//                .withUser("admin").password("admin").authorities("ROLE_USER","ROLE_ADMIN");
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // Disable CSRF (cross site request forgery) - phising
        http.csrf().disable();

        http
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers( "/userValidation" ).permitAll()


                .antMatchers("/index").permitAll()
                .antMatchers("/admin**").access("hasRole('ROLE_ADMIN')")
//                .antMatchers("/student/**").access("hasRole('ROLE_STUDENT')")
//                .antMatchers("/professor/**").access("hasRole('ROLE_PROFESSOR')")

                .anyRequest().authenticated()

                .and()
                .formLogin().loginPage("/login").permitAll()
                .defaultSuccessUrl("/index")
//                .failureUrl("/loginPage?error")

                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(databaseUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
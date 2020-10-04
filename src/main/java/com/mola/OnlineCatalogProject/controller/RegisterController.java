package com.mola.OnlineCatalogProject.controller;

import com.mola.OnlineCatalogProject.model.PendingUser;
import com.mola.OnlineCatalogProject.model.Role;
import com.mola.OnlineCatalogProject.model.User;
import com.mola.OnlineCatalogProject.repository.PendingUserRepository;
import com.mola.OnlineCatalogProject.repository.RoleRepository;
import com.mola.OnlineCatalogProject.repository.UserRepository;
import com.mola.OnlineCatalogProject.service.RandomStringGenerator;
import com.mola.OnlineCatalogProject.service.SendGridEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class RegisterController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PendingUserRepository pendingUserRepository;

    @Autowired
    private RandomStringGenerator randomStringGenerator;

    @Autowired
    private SendGridEmailService sendGridEmailService;

    @GetMapping("/register")
    public String registerUser() {
        return "security/register";
    }


    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @PostMapping("/register")
    public String registerUser(String username, String password, String email) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(encoder().encode(password));
        user.setUsername(username);

//        Role role = new Role();
//        role.setRoleName("ROLE_ADMIN");
//        RoleRepository.save(role);
//        user.setEmail("admin@admin.com");
//        user.setPassword(passwordEncoderCLI().encode("admin"));
//        user.setUsername("admin");
//        user.setRole(role);
//        userRepository.save(user);

        userRepository.save(user);
        PendingUser pendingUser = new PendingUser();
        String activationCode = randomStringGenerator.getAlphaNumericString(20);
        pendingUser.setActivationCode(activationCode);
        sendGridEmailService.sendHTML("testmail.java20@gmail.com",
                user.getEmail(), "Please confirm account",
                randomStringGenerator.linkCreator(activationCode,
                        "https://online-school-catalog-rg.herokuapp.com"));
        pendingUser.setUser(user);
        pendingUserRepository.save(pendingUser);

        return "redirect:/login";
    }

    @GetMapping("/userValidation")
    public String validateUser(String activationCode) {
        System.out.println(activationCode);
        Optional<PendingUser> optional = pendingUserRepository.findByActivationCode(activationCode);
        if(optional.isPresent()) {

            PendingUser pendingUser = optional.get();
            System.out.println(pendingUser.getActivationCode());

            pendingUserRepository.delete(pendingUser);
        }
        return "security/login";

    }
}

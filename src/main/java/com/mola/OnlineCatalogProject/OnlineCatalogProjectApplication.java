package com.mola.OnlineCatalogProject;

import com.mola.OnlineCatalogProject.model.PendingUser;
import com.mola.OnlineCatalogProject.model.Role;
import com.mola.OnlineCatalogProject.model.User;
import com.mola.OnlineCatalogProject.repository.PendingUserRepository;
import com.mola.OnlineCatalogProject.repository.RoleRepository;
import com.mola.OnlineCatalogProject.repository.StudentRepository;
import com.mola.OnlineCatalogProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

@SpringBootApplication
public class OnlineCatalogProjectApplication implements CommandLineRunner {

    @Autowired
    private StudentRepository studentRepository;

    @Bean
    public BCryptPasswordEncoder encoderGUI() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PendingUserRepository pendingUserRepository;

//    @Bean
//    public SpringSecurityDialect securityDialect() {
//        return new SpringSecurityDialect();
//    }

    public static void main(String[] args) {
        SpringApplication.run(OnlineCatalogProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//		Role role = new Role();
//      Role role = new Role();
//      role.setRoleName("ROLE_USER");
//      roleRepository.save(role);

        for (PendingUser p : pendingUserRepository.findAll()
        ) {
            pendingUserRepository.delete(p);
        }
        User user = userRepository.findByUsername("laura").get();
        user.setEmailAddress("laura93.m@yahoo.com");
        userRepository.save(user);

        User user1 = userRepository.findByUsername("giorgiana").get();
        user1.setEmailAddress("testmail.java20@gmail.com");
        userRepository.save(user1);

//        for (User user : userRepository.findAll()
//        ) {
//            if (!user.getUsername().equals("test")) {
//               user.setUsername("test");
//               user.setRole(roleRepository.findByRoleName("ROLE_USER").get());
//
//		User user = userRepository.findByUsername("test").get();
//		user.setUsername("test");
//		user.setEmailAddress("test@test.com");
//		user.setPassword(encoderGUI().encode("test"));


		user.setRole(roleRepository.findByRoleName("ROLE_ADMIN").get());
		userRepository.save(user);

        user1.setRole(roleRepository.findByRoleName("ROLE_ADMIN").get());
        userRepository.save(user1);
    }
}
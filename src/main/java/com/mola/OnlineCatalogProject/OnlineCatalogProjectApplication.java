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

    public static void main(String[] args) {
        SpringApplication.run(OnlineCatalogProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Role role = new Role();
        role.setRoleName("ROLE_USER");
        roleRepository.save(role);

        for (PendingUser p : pendingUserRepository.findAll()
        ) {
            pendingUserRepository.delete(p);
        }

        for (User user : userRepository.findAll()
        ) {
            if (!user.getUsername().equals("test")) {
//                user.setUsername("test");
                user.setRole(roleRepository.findByRoleName("ROLE_USER").get());
            }
        }

//		User user = userRepository.findByUsername("test").get();
//		user.setUsername("test");
//		user.setEmail("test@test.com");
//		user.setPassword(encoderGUI().encode("test"));
//
////		user.setRole(roleRepository.findByRoleName("ROLE_ADMIN").get());
//		userRepository.save(user);

    }
}
